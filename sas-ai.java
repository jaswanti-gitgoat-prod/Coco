import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.security.*;
import java.sql.*;
import java.util.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/*
    Intentionally Vulnerable Java File
    ----------------------------------
    PURPOSE:
    This file is designed ONLY for:
      - SAST testing
      - AI security review testing
      - Rule tuning
      - Security education

    DO NOT deploy this code.
*/

public class HugeVulnerableJavaFile {

    // ----------------------------------------------------------------------
    // HARDCODED SECRETS
    // ----------------------------------------------------------------------

    private static final String AWS_ACCESS_KEY = "AKIAIOSFODNN7EXAMPLE";
    private static final String AWS_SECRET_KEY = "wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY";
    private static final String DB_PASSWORD = "SuperSecretPassword123";
    private static final String GITHUB_TOKEN = "ghp_exampleVeryLongGitHubToken123456789";
    private static final String JWT_SECRET = "super-secret-jwt-key";
    private static final String STRIPE_SECRET = "sk_test_51Nexample";

    // ----------------------------------------------------------------------
    // WEAK RANDOMNESS
    // ----------------------------------------------------------------------

    public static String generateWeakToken() {
        Random random = new Random();
        return "TOKEN-" + random.nextInt(999999);
    }

    // ----------------------------------------------------------------------
    // WEAK CRYPTOGRAPHY
    // ----------------------------------------------------------------------

    public static String md5Hash(String input) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digest = md.digest(input.getBytes());

        StringBuilder sb = new StringBuilder();

        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }

    public static String sha1Hash(String input) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] digest = md.digest(input.getBytes());

        StringBuilder sb = new StringBuilder();

        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }

    public static byte[] weakDesEncryption(String data) throws Exception {
        byte[] keyBytes = "12345678".getBytes();
        SecretKey key = new SecretKeySpec(keyBytes, "DES");

        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        return cipher.doFinal(data.getBytes());
    }

    // ----------------------------------------------------------------------
    // SQL INJECTION
    // ----------------------------------------------------------------------

    public static void vulnerableLogin(String username, String password) {
        try {
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/app",
                "root",
                DB_PASSWORD
            );

            Statement stmt = conn.createStatement();

            String query =
                "SELECT * FROM users WHERE username='" + username +
                "' AND password='" + password + "'";

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                System.out.println("User authenticated");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ----------------------------------------------------------------------
    // COMMAND INJECTION
    // ----------------------------------------------------------------------

    public static void pingHost(String host) {
        try {
            Runtime.getRuntime().exec("ping -c 1 " + host);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void runSystemCommand(String cmd) {
        try {
            ProcessBuilder pb = new ProcessBuilder("sh", "-c", cmd);
            pb.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ----------------------------------------------------------------------
    // PATH TRAVERSAL
    // ----------------------------------------------------------------------

    public static String readUserFile(String filename) throws Exception {
        Path path = Paths.get("uploads/" + filename);
        return new String(Files.readAllBytes(path));
    }

    // ----------------------------------------------------------------------
    // SSRF
    // ----------------------------------------------------------------------

    public static void fetchUrl(String targetUrl) {
        try {
            URL url = new URL(targetUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                new InputStreamReader(conn.getInputStream())
            );

            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
            }

            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ----------------------------------------------------------------------
    // XXE
    // ----------------------------------------------------------------------

    public static void parseXml(String xmlPath) {
        try {
            javax.xml.parsers.DocumentBuilderFactory dbf =
                javax.xml.parsers.DocumentBuilderFactory.newInstance();

            javax.xml.parsers.DocumentBuilder db = dbf.newDocumentBuilder();
            db.parse(new File(xmlPath));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ----------------------------------------------------------------------
    // LDAP INJECTION
    // ----------------------------------------------------------------------

    public static void ldapSearch(String username) {
        try {
            Hashtable<String, String> env = new Hashtable<>();
            env.put(Context.INITIAL_CONTEXT_FACTORY,
                "com.sun.jndi.ldap.LdapCtxFactory");

            InitialContext ctx = new InitialContext(env);

            String filter = "(uid=" + username + ")";

            System.out.println("LDAP Filter: " + filter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ----------------------------------------------------------------------
    // OPEN REDIRECT
    // ----------------------------------------------------------------------

    public static void redirectUser(String url) {
        System.out.println("Redirecting user to: " + url);
    }

    // ----------------------------------------------------------------------
    // XSS-LIKE UNSAFE OUTPUT
    // ----------------------------------------------------------------------

    public static void renderComment(String comment) {
        System.out.println("<div>" + comment + "</div>");
    }

    // ----------------------------------------------------------------------
    // INSECURE DESERIALIZATION
    // ----------------------------------------------------------------------

    public static Object deserialize(byte[] data) {
        try {
            ObjectInputStream ois = new ObjectInputStream(
                new ByteArrayInputStream(data)
            );

            return ois.readObject();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // ----------------------------------------------------------------------
    // LOG INJECTION
    // ----------------------------------------------------------------------

    public static void logUser(String username) {
        System.out.println("User login: " + username);
    }

    // ----------------------------------------------------------------------
    // TEMP FILE ISSUE
    // ----------------------------------------------------------------------

    public static void createTempFile() throws Exception {
        File file = File.createTempFile("temp", ".txt");

        FileWriter fw = new FileWriter(file);
        fw.write("Sensitive Data");
        fw.close();
    }

    // ----------------------------------------------------------------------
    // INSECURE COOKIE
    // ----------------------------------------------------------------------

    public static String generateSessionCookie() {
        return "SESSIONID=admin-session; path=/";
    }

    // ----------------------------------------------------------------------
    // DEBUG ENABLED
    // ----------------------------------------------------------------------

    public static void printDebugInfo() {
        System.out.println("DEBUG MODE ENABLED");
        System.out.println("DB PASSWORD: " + DB_PASSWORD);
    }

    // ----------------------------------------------------------------------
    // INFORMATION DISCLOSURE
    // ----------------------------------------------------------------------

    public static void exposeEnvironmentVariables() {
        Map<String, String> env = System.getenv();

        for (String key : env.keySet()) {
            System.out.println(key + "=" + env.get(key));
        }
    }

    // ----------------------------------------------------------------------
    // NULL PASSWORD AUTHENTICATION
    // ----------------------------------------------------------------------

    public static boolean authenticate(String username, String password) {
        if (password == null || password.equals("")) {
            return true;
        }

        return false;
    }

    // ----------------------------------------------------------------------
    // TRUSTING USER INPUT
    // ----------------------------------------------------------------------

    public static void deleteFile(String filename) {
        File file = new File(filename);
        file.delete();
    }

    // ----------------------------------------------------------------------
    // HARDCODED ADMIN USER
    // ----------------------------------------------------------------------

    public static boolean isAdmin(String username) {
        return username.equals("admin");
    }

    // ----------------------------------------------------------------------
    // UNSAFE REFLECTION
    // ----------------------------------------------------------------------

    public static Object unsafeReflection(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            return clazz.newInstance();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // ----------------------------------------------------------------------
    // SCRIPT ENGINE EXECUTION
    // ----------------------------------------------------------------------

    public static void executeJavaScript(String script) {
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("JavaScript");

            engine.eval(script);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ----------------------------------------------------------------------
    // DIRECTORY LISTING
    // ----------------------------------------------------------------------

    public static void listDirectory(String path) {
        File dir = new File(path);

        for (File file : dir.listFiles()) {
            System.out.println(file.getName());
        }
    }

    // ----------------------------------------------------------------------
    // RACE CONDITION EXAMPLE
    //
