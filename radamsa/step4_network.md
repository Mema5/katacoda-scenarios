Almost there! Now let's see another example.

## A java server
To showcase more advanced features of Radamsa we created an java server called `Server.java`. The server takes an http request and checks if the request contains a cookie. If the request contains a cookie the server will answer `hello, old user`. If the request does not contain a cookie the server will give the client a cookie and answer with `hello, new user`.

<pre class="file">
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
        System.out.println("succeeded to listen to port: " + this.port);

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
                    out.println("hello, old user");
                }
              
            }catch (IOException e){
                e.printStackTrace();
            }
            in.close();
            out.close();
        }
    }
    public static void main(String[] args) throws IOException {
        Server server = new Server(8080);
        server.run();
    }
}
</pre>

The program can be compiled with `javac Server.java`{{execute}}. To run the program use the `java Server`{{execute}} command. The server is now listening on port 8080 on localhost.

## Try the server
To try the server we can use the command `curl localhost:8080`{{execute T2}} this will open a new terminal and should result in a response from the server.

## Fuzz the server
With the `-o` option, you can decide where Radamsa should output to. This means that Radamsa can be used to fuzz a server by acting as a client. 
With the server running on port 8080 on localhost we can run the command `radamsa -o 127.0.0.1:8080 -n 1 http`{{execute T2}} With the `-o` option we tell radamsa where to output, with `-n` we tell radamsa how many times it should run. The `http` file is a premade file used to generate the fuzzing from radamsa, it contains the following

```GET / HTTP/1.1
Host: localhost:8080
Connection: keep-alive
Cache-Control: max-age=0
DNT: 1
Upgrade-Insecure-Requests: 1
User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.163 Safari/537.36
Sec-Fetch-Dest: document
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9
Sec-Fetch-Site: none
Sec-Fetch-Mode: navigate
Sec-Fetch-User: ?1
Accept-Encoding: gzip, deflate, br
Accept-Language: en-US,en;q=0.9,sv;q=0.8
Cookie: client_id=0
```
After running the command you can open `terminal window 1` to see the request you sent to the server.

In most cases we want try many different request to the server to find bugs, we can tell radamsa to run an infinite amount of times by using `-n inf`
`radamsa -o 127.0.0.1:8080 -n inf http`{{execute T2}} We can now fuzz for however long we want. You will probably find an exception quite soon after starting running the radamsa command. Both the server and the radamsa script can be stopped with <kbd>Ctrl</kbd> + <kbd>C</kbd>.
