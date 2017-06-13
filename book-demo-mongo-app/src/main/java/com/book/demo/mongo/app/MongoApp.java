package com.book.demo.mongo.app;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.bson.Document;

import com.book.demo.mongo.app.mongo.MongoDBUtil;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;

public class MongoApp extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	JButton add, select;
	JTable table;
	Object body[][] = new Object[50][4];
	JTabbedPane tp;
	TextArea ta;

	public MongoApp() {
		super("mongodb操作");
		this.setSize(400, 300);
		this.setLocation(300, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel ps = new JPanel();
		add = new JButton("添加");
		select = new JButton("显示");
		add.addActionListener(this);
		select.addActionListener(this);
		ps.add(add);
		ps.add(select);
		tp = new JTabbedPane();
		ta = new TextArea();
		tp.add(ta);
		this.getContentPane().add(tp, "Center");
		this.getContentPane().add(ps, "South");
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new MongoApp();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == add) {
			add();
		}
		if (e.getSource() == select) {
			select();
		}
	}

	public void select() {
		try {
			JTextField t[] = new JTextField[4];
			t[0] = new JTextField("输入数据库名:");
			t[0].setEditable(false);
			t[1] = new JTextField();
			t[2] = new JTextField("输入集合名:");
			t[2].setEditable(false);
			t[3] = new JTextField();
			String but[] = { "确定", "取消" };
			int go = JOptionPane.showOptionDialog(null, t, "插入信息",
					JOptionPane.YES_OPTION, JOptionPane.INFORMATION_MESSAGE,
					null, but, but[0]);
			if (go == 0) {
				try {
					final StringBuffer jsonst = new StringBuffer();
					MongoClient mongoClient = MongoDBUtil.initMongo();
					FindIterable<Document> iter = mongoClient
							.getDatabase(t[1].getText())
							.getCollection(t[3].getText()).find();
					iter.forEach(new Block<Document>() {
						public void apply(Document doc) {
							jsonst.append(doc.toJson());
						}
					});
					ta.setText(jsonst.toString());
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
			JTextField t[] = new JTextField[8];
			t[0] = new JTextField("输入数据库名:");
			t[0].setEditable(false);
			t[1] = new JTextField();
			t[2] = new JTextField("输入集合名:");
			t[2].setEditable(false);
			t[3] = new JTextField();
			t[4] = new JTextField("key:");
			t[4].setEditable(false);
			t[5] = new JTextField();
			t[6] = new JTextField("value:");
			t[6].setEditable(false);
			t[7] = new JTextField();
			String but[] = { "确定", "取消" };
			int go = JOptionPane.showOptionDialog(null, t, "插入信息",
					JOptionPane.YES_OPTION, JOptionPane.INFORMATION_MESSAGE,
					null, but, but[0]);
			if (go == 0) {
				try {
					final StringBuffer jsonst = new StringBuffer();
					MongoClient mongoClient = MongoDBUtil.initMongo();
					Document document = new Document();
					String key = new String(t[5].getText().getBytes(
							"ISO-8859-1"), "GBK");
					String value = new String(t[7].getText().getBytes(
							"ISO-8859-1"), "GBK");
					document.append(key, value);
					mongoClient.getDatabase("GoodsTest").getCollection("goods")
							.insertOne(document);
					ta.setText(jsonst.toString());
				} catch (Exception ee) {
					ee.printStackTrace();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
