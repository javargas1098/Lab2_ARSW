package eci.edu.Lab2.trigonometrica;

import java.net.*;
import java.util.StringTokenizer;
import java.io.*;

public class EchoServer {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(35000);
		} catch (IOException e) {
			System.err.println("Could not listen on port: 35000.");
			System.exit(1);
		}
		Socket clientSocket = null;
		try {
			clientSocket = serverSocket.accept();
		} catch (IOException e) {
			System.err.println("Accept failed.");
			System.exit(1);
		}
		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		String inputLine, outputLine;
		Double result = null;
		String operando = "cos";
		Integer num = null;
		
		// Fragmento de codigo realizado por Javier Vargas y Sebastian Goenaga
		while ((inputLine = in.readLine()) != null) {
			StringTokenizer stdInTok = new StringTokenizer(inputLine);

			String oper = stdInTok.nextToken();

			try {
				num = Integer.parseInt(oper);
			} catch (Exception e) {
				num = Integer.parseInt(stdInTok.nextToken());
				operando = oper;
			}

			if (operando.equals("sin")) {
				result = Math.sin(Math.toRadians(num));
			} else if (operando.equals("cos")) {
				result = Math.cos(Math.toRadians(num));
			} else if (operando.equals("tan")) {
				result = Math.tan(Math.toRadians(num));
			}

			System.out.println("Mensaje:" + inputLine);
			outputLine = "Respuesta: " + result;
			out.println(outputLine);
			if (outputLine.equals("Respuesta: Bye."))
				break;
		}
		out.close();
		in.close();
		clientSocket.close();
		serverSocket.close();
	}
}