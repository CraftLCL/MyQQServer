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
	//定义文件域，用于存放服务器端的一些日志信息
	private JTextArea infoText;
	//启动按钮
	private JButton runButton;
	//关闭按钮
	private JButton stopButton;

	public void launchServer() {
		this.setSize(400, 600);
		this.infoText = new JTextArea();
		this.infoText.setEditable(false);
		this.infoText.append("单击启动按钮启动服务器，如需关闭单击关闭按钮...\n");
		JScrollPane scroll = new JScrollPane(this.infoText);
		this.add(scroll, BorderLayout.CENTER);
		JPanel bottonPanel = new JPanel();
		//创建按钮的监听器对象
		ButtonMonitor bm = new ButtonMonitor();
		this.runButton = new JButton("启动");
		this.runButton.setActionCommand("1");
		//为启动按钮注册监听
		this.runButton.addActionListener(bm);
		this.stopButton = new JButton("关闭");
		this.stopButton.setActionCommand("2");
		//为关闭按钮注册监听
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
				//添加代码，创建服务器端主线程对象，并启动服务器主线程。



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
