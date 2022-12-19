package com.artisan.view;
import com.artisan.dao.worksDao;
import com.artisan.util.DbUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.artisan.model.works;
import com.artisan.util.StringUtil;

public class MainFrm extends JFrame {
	JLayeredPane pane = new JLayeredPane();
	JLabel label;
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JButton add = new JButton("添加");
	JButton delete = new JButton("删除");
	private JTable worksTable;
	private JTextArea workDescTxt ;
	private DbUtil dbUtil=new DbUtil();
	private worksDao worksDao =new worksDao();
	private JTextField s_workNameTxt;
	private JTextField idTxt;
	private JTextField workNameTxt;
	private JDesktopPane table =null;


	ImageIcon image;
	/**
	 * Create the frame.
	 */
	public MainFrm(){
		image = new ImageIcon("src/images/bg.png");
		//设置组件透明
		label = new JLabel(image);		//把背景图片添加到标签里
		panel1 = (JPanel)this.getContentPane(); 	//把我的面板设置为内容面板
		panel1.add(label);
		add.setBounds(50, 700, 100, 50);
		pane.add(panel1,JLayeredPane.DEFAULT_LAYER);
		pane.add(add, JLayeredPane.MODAL_LAYER);
		delete.setBounds(300, 700, 100, 50);
		pane.add(panel2,JLayeredPane.DEFAULT_LAYER);
		pane.add(delete, JLayeredPane.MODAL_LAYER);
		//添加按钮
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bookTypeAddActionPerformed(arg0);

			}
		});
		//删除按钮
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DeleteActionEvent(arg0);

			}
		});
		this.setTitle("xx视频主页");
		this.setBounds(100,100,image.getIconWidth(), 800);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayeredPane(pane);
		this.setVisible(true);
		JScrollPane scrollPane = new JScrollPane();

		JLabel lblNewLabel = new JLabel("作品名称       ");

		JLabel lblNewLabel_1 = new JLabel("作品信息描述");
		JLabel label = new JLabel("作品名称");

		s_workNameTxt = new JTextField();
		s_workNameTxt.setColumns(10);
		workNameTxt = new JTextField();
		workNameTxt.setColumns(10);
		workDescTxt = new JTextArea();
		workDescTxt.setLineWrap(true);
		workDescTxt.setWrapStyleWord(true);

		JButton button = new JButton("查询");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				worksSearchActionPerformed(e);
			}

		});
		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addGap(22)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
										.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
												.addComponent(label)
												.addGap(18)
												.addComponent(s_workNameTxt, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)
												.addGap(18)
												.addComponent(button))
										.addGap(20)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
														.addComponent(lblNewLabel)
														.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
														.addComponent(workNameTxt, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
												.addGroup(groupLayout.createSequentialGroup()
														.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																.addComponent(lblNewLabel_1))
														.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
														.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																.addComponent(workDescTxt))))

										.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE))

								.addGap(48))
		);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addGap(300)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(label)
										.addComponent(s_workNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(button))
								.addGap(19)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGap(12)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblNewLabel)
										.addComponent(workNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(29)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_1)
										.addComponent(workDescTxt, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE))
								.addGap(28))
		);
		getContentPane().setLayout(groupLayout);



		idTxt = new JTextField();
		idTxt.setEditable(false);
		idTxt.setColumns(10);

		JLabel label_1 = new JLabel("作品名称");

		worksTable = new JTable();
		worksTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				worksTableMousePressed(e);
			}
		});
		worksTable.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"编号", "作品名称", "作品描述"
				}
		) {
			boolean[] columnEditables = new boolean[] {
					false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		scrollPane.setViewportView(worksTable);
		getContentPane().setLayout(groupLayout);

		this.fillTable(new works());
	}

	/**
	 * 表格行点击事件处理
	 * @param e
	 */
	private void worksTableMousePressed(MouseEvent evt) {
		int row=worksTable.getSelectedRow();
		idTxt.setText((String)worksTable.getValueAt(row, 0));
		workNameTxt.setText((String)worksTable.getValueAt(row, 1));
		workDescTxt.setText((String)worksTable.getValueAt(row, 2));
	}

	/**
	 * 班级信息搜索事件处理
	 * @param evt
	 */
	private void worksSearchActionPerformed(ActionEvent evt) {
		String s_workName=this.s_workNameTxt.getText();
		works works=new works();
		works.setworkName(s_workName);
		this.fillTable(works);
	}

	/**
	 * 初始化表格
	 * @param works
	 */
	private void fillTable(works works){
		DefaultTableModel dtm=(DefaultTableModel) worksTable.getModel();
		dtm.setRowCount(0); // 设置成0行
		Connection con=null;
		try{
			con=dbUtil.getCon();
			ResultSet rs= worksDao.list(con, works);
			while(rs.next()){
				Vector v=new Vector();
				v.add(rs.getString("id"));
				v.add(rs.getString("worksName"));
				v.add(rs.getString("worksDesc"));
				dtm.addRow(v);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	private void bookTypeAddActionPerformed(ActionEvent evt) {
		String workName=this.workNameTxt.getText();
		String workDesc=this.workDescTxt.getText();
		if(StringUtil.isEmpty(workName)){
			JOptionPane.showMessageDialog(null, "作品名称不能为空！");
			return;
		}
		works works=new works(workName,workDesc);
		Connection con=null;
		try{
			con=dbUtil.getCon();
			int n=worksDao.add(con, works);
			if(n==1){
				JOptionPane.showMessageDialog(null, "作品添加成功！");

			}else{
				JOptionPane.showMessageDialog(null, "作品信息添加失败！");
			}
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "作品信息添加失败！");
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private void DeleteActionEvent(ActionEvent evt) {
		String id=idTxt.getText();
		if(StringUtil.isEmpty(id)){
			JOptionPane.showMessageDialog(null, "请选择要删除的记录");
			return;
		}
		int n=JOptionPane.showConfirmDialog(null, "确定要删除该记录吗？");
		if(n==0){
			Connection con=null;
			try{
				con=dbUtil.getCon();
				int deleteNum=worksDao.delete(con, id);
				if(deleteNum==1){
					JOptionPane.showMessageDialog(null, "删除成功");
					this.fillTable(new works());
				}else{
					JOptionPane.showMessageDialog(null, "删除失败");
				}
			}catch(Exception e){
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "删除失败");
			}finally{
				try {
					dbUtil.closeCon(con);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	}