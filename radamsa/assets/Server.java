import java.net.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Server {
    private int port;
    private Socket socket = null;
    private BufferedReader in = null;
    private ServerSocket serverSocket = null;
    private PrintWriter out = null;
    private int client_id = 0;

    public Server(int port) {
        this.port = port;
    }

    public void run() throws IOException {
        String default_http = "HTTP/1.1 200 OK\nContent-Type: text/plain\n";
        try {
            serverSocket = new ServerSocket(this.port);
        } catch (IOException e) {
            System.err.println("failed to listen to port: " + this.port);
            System.exit(1);
        }
        System.out.println("succeded to listen to port: " + this.port);

        while (true) {
            try {
                socket = serverSocket.accept();
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String get = "";
                String line = " ";
                int has_read_id = -1;

                while (in.ready()) {
                    System.out.println(line);
                    if (line.equals("GET / HTTP/1.1")){
                        get = line;
                    }
                
                   // get user id of a user
                   if (line.contains("Cookie")){
                        Matcher matcher = Pattern.compile("client_id=(\\d*)").matcher(line);
                        matcher.find();
                        client_id = Integer.parseInt(matcher.group(1));
                        has_read_id = 1;
                    }
                    line = in.readLine();
                }
                // if new user
                if (has_read_id == -1){
                    if ("GET / HTTP/1.1".equals(get)){
                        out.println("HTTP/1.1 200 OK\nContent-Type: text/plain\nSet-Cookie: client_id="
                        + client_id +"\n\n");
                        out.println("hello, new user");
                        client_id += 1;

                        }
                    }
                // if returning user
                else if (("GET / HTTP/1.1".equals(get)) && (has_read_id != -1)){
                    out.println(default_http + "\n\n");
                    out.println("hello, old user!");
                }
              
            }catch (IOException e){
                e.printStackTrace();
            }
            in.close();
            out.close();

        }
    }
    public static void main(String[] args) throws IOException {
        Server server = new Server(8989);
        server.run();
    }
}
