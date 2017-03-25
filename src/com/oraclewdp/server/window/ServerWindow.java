package com.oraclewdp.server.window;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ServerWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	//�����ļ������ڴ�ŷ������˵�һЩ��־��Ϣ
	private JTextArea infoText;
	//������ť
	private JButton runButton;
	//�رհ�ť
	private JButton stopButton;

	public void launchServer() {
		this.setSize(400, 600);
		this.infoText = new JTextArea();
		this.infoText.setEditable(false);
		this.infoText.append("����������ť����������������رյ����رհ�ť...\n");
		JScrollPane scroll = new JScrollPane(this.infoText);
		this.add(scroll, BorderLayout.CENTER);
		JPanel bottonPanel = new JPanel();
		//������ť�ļ���������
		ButtonMonitor bm = new ButtonMonitor();
		this.runButton = new JButton("����");
		this.runButton.setActionCommand("1");
		//Ϊ������ťע�����
		this.runButton.addActionListener(bm);
		this.stopButton = new JButton("�ر�");
		this.stopButton.setActionCommand("2");
		//Ϊ�رհ�ťע�����
		this.stopButton.addActionListener(bm);
		bottonPanel.add(this.runButton);
		bottonPanel.add(this.stopButton);
		this.add(bottonPanel, BorderLayout.SOUTH);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private class ButtonMonitor implements ActionListener {
		Server server = new Server(infoText);
		@Override
		public void actionPerformed(ActionEvent e) {
			String actionCommand = e.getActionCommand();
			if (actionCommand.equals("1")) {
				//��Ӵ��룬���������������̶߳��󣬲��������������̡߳�



			}else if(actionCommand.equals("2")){
				server.shutDown();
			}
		}
	}

	public static void main(String[] args) {
		ServerWindow sw = new ServerWindow();
		sw.launchServer();
	}
}
