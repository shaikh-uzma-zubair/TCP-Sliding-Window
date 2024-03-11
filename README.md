# TCP-Sliding-Window

## Overview

This project implements a basic TCP client and server in Java to demonstrate the TCP Sliding Window protocol. The client and server communicate over a network, exchanging data in a sliding window fashion. This project was developed as part of the CS 258 course assignment instructed by Prof. Navrati Saxena.

## Team Members

- Aniruddha Prabhash Chakravarty (017102306)
- Uzma Zubair Shaikh (016605485)

## Requirements

- Java Development Kit (JDK)

## Usage

1. **Server Setup**: Compile and run the server script on the machine designated as the server.
    ```bash
    javac Server.java
    java Server
    ```

2. **Client Setup**: Compile and run the client script on the machine designated as the client, providing the server's IP address and port number as arguments.
    ```bash
    javac Client.java
    java Client <server_ip> <port_number>
    ```

3. **Communication**: Upon successful connection, the client will send an initial string to the server. The server will respond with a "success" message. Then, the client will begin sending TCP sequence numbers in a sliding window manner. The server will respond with corresponding ACK numbers.

4. **Packet Dropping**: The client will probabilistically drop 1% of the packets while sending. The server will keep track of missing packets.

5. **Retransmission**: After a specific time (e.g., 100 sequence numbers), the client will retransmit any dropped packets with the same probability.

6. **Output**: The server will maintain a count of received packets and missing packets. Periodically, after every 1000 packets received at the server, it will calculate the good-put (received packets/sent packets) and report the average good-put.

## Note

- The program is designed to handle 10,000,000 packets, with a maximum sequence number limited to 2 <sup> 16 </sup>.

## Disclaimer

This project is for educational purposes and may not be suitable for production environments. Use it at your own risk.
