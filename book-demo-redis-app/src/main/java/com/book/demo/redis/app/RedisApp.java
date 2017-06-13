package com.book.demo.redis.app;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import redis.clients.jedis.Jedis;

import com.book.demo.redis.app.redis.RedisUtil;

public class RedisApp extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	JButton add, select, del;
	JTable table;
	Object body[][] = new Object[50][4];
	Connection conn;
	Statement stat;
	ResultSet rs;
	JTabbedPane tp;
	TextArea ta;

	public RedisApp() {
		super("redis操作");
		this.setSize(400, 300);
		this.setLocation(300, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel ps = new JPanel();
		add = new JButton("添加");
		select = new JButton("显示");
		del = new JButton("删除");
		add.addActionListener(this);
		select.addActionListener(this);
		del.addActionListener(this);
		ps.add(add);
		ps.add(select);
		ps.add(del);
		tp = new JTabbedPane();
		ta = new TextArea();
		tp.add(ta);
		this.getContentPane().add(tp, "Center");
		this.getContentPane().add(ps, "South");
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new RedisApp();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == add) {
			add();
		}
		if (e.getSource() == select) {
			select();
		}
		if (e.getSource() == del) {
			del();
		}
	}

	public void del() {
		try {
			JTextField t[] = new JTextField[2];
			t[0] = new JTextField("输入key:");
			t[0].setEditable(false);
			t[1] = new JTextField();
			String but[] = { "确定", "取消" };
			int go = JOptionPane.showOptionDialog(null, t, "插入信息",
					JOptionPane.YES_OPTION, JOptionPane.INFORMATION_MESSAGE,
					null, but, but[0]);
			if (go == 0) {
				try {
					String key = new String(t[1].getText().getBytes(
							"ISO-8859-1"), "GBK");
					Jedis jedis = RedisUtil.initPool().getResource();
					jedis.del(key);
					JOptionPane.showMessageDialog(null, "数据已成功删除！");
				} catch (Exception ee) {
					JOptionPane.showMessageDialog(null, "删除数据错误！");
					ee.printStackTrace();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void select() {
		try {
			JTextField t[] = new JTextField[2];
			t[0] = new JTextField("输入key:");
			t[0].setEditable(false);
			t[1] = new JTextField();
			String but[] = { "确定", "取消" };
			int go = JOptionPane.showOptionDialog(null, t, "插入信息",
					JOptionPane.YES_OPTION, JOptionPane.INFORMATION_MESSAGE,
					null, but, but[0]);
			if (go == 0) {
				try {
					String key = new String(t[1].getText().getBytes(
							"ISO-8859-1"), "GBK");
					Jedis jedis = RedisUtil.initPool().getResource();
					String value = jedis.get(key);
					ta.setText(value);
				} catch (Exception ee) {
					ee.printStackTrace();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void add() {
		try {
			JTextField t[] = new JTextField[4];
			t[0] = new JTextField("输入key:");
			t[0].setEditable(false);
			t[1] = new JTextField();
			t[2] = new JTextField("输入value:");
			t[2].setEditable(false);
			t[3] = new JTextField();
			String but[] = { "确定", "取消" };
			int go = JOptionPane.showOptionDialog(null, t, "插入信息",
					JOptionPane.YES_OPTION, JOptionPane.INFORMATION_MESSAGE,
					null, but, but[0]);
			if (go == 0) {
				try {
					String key = new String(t[1].getText().getBytes(
							"ISO-8859-1"), "GBK");
					String value = new String(t[3].getText().getBytes(
							"ISO-8859-1"), "GBK");
					Jedis jedis = RedisUtil.initPool().getResource();
					jedis.set(key, value);
					JOptionPane.showMessageDialog(null, "数据已成功插入！");
				} catch (Exception ee) {
					JOptionPane.showMessageDialog(null, "插入数据错误！");
					ee.printStackTrace();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}