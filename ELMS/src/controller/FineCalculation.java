    JButton return_book=new JButton("Return Book");
    return_book.setBounds(280,60,160,25); 
     
    return_book.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
                 
                JFrame g = new JFrame("Enter Details");
                g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                //set labels 
                JLabel l1,l2,l3,l4;  
                l1=new JLabel("Issue ID(IID)");
                l1.setBounds(30,15, 100,30); 
                
                 
                l4=new JLabel("Return Date(DD-MM-YYYY)");  
                l4.setBounds(30,50, 150,30); 
                 
                JTextField F_iid = new JTextField();
                F_iid.setBounds(110, 15, 200, 30);
                 
                 
                JTextField F_return=new JTextField();
                F_return.setBounds(180, 50, 130, 30);
             
 
                JButton create_but=new JButton("Return");
                create_but.setBounds(130,170,80,25);
                create_but.addActionListener(new ActionListener() {
                     
                    public void actionPerformed(ActionEvent e){                 
                     
                    String iid = F_iid.getText();
                    String return_date = F_return.getText();
                     
                    Connection connection = connect();
                     
                    try {
                    Statement stmt = connection.createStatement();
                     stmt.executeUpdate("USE LIBRARY");
                     String date1=null;
                     String date2=return_date;
                     
                     ResultSet rs = stmt.executeQuery("SELECT ISSUED_DATE FROM ISSUED WHERE IID="+iid);
                     while (rs.next()) {
                         date1 = rs.getString(1);
                          
                       }
                      
                     try {
                            Date date_1=new SimpleDateFormat("dd-MM-yyyy").parse(date1);
                            Date date_2=new SimpleDateFormat("dd-MM-yyyy").parse(date2);
                            long diff = date_2.getTime() - date_1.getTime();
                            ex.days=(int)(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
                             
                             
                        } catch (ParseException e1) {
                            e1.printStackTrace();
                        }
                      
                     stmt.executeUpdate("UPDATE ISSUED SET RETURN_DATE='"+return_date+"' WHERE IID="+iid);
                     g.dispose();
                      
 
                     Connection connection1 = connect();
                     Statement stmt1 = connection1.createStatement();
                     stmt1.executeUpdate("USE LIBRARY");                
                    ResultSet rs1 = stmt1.executeQuery("SELECT PERIOD FROM ISSUED WHERE IID="+iid); 
                    String diff=null; 
                    while (rs1.next()) {
                         diff = rs1.getString(1);
                        }
                    int diff_int = Integer.parseInt(diff);
                    if(ex.days&amp;amp;amp;amp;amp;amp;amp;amp;amp;amp;gt;diff_int) { 
                         
                        System.out.println(ex.days);
                        int fine = (ex.days-diff_int)*X; 
                        stmt1.executeUpdate("UPDATE ISSUED SET FINE="+fine+" WHERE IID="+iid);  
                        String fine_str = ("Fine: Rs. "+fine);
                        JOptionPane.showMessageDialog(null,fine_str);
                         --7
                    }
 
                     JOptionPane.showMessageDialog(null,"Book Returned!");
                      
                    }
                             
                     
                    catch (SQLException e1) {
                         JOptionPane.showMessageDialog(null, e1);
                    }   
                     
                    }
                     
                }); 
                    g.add(l4);
                    g.add(create_but);
                    g.add(l1);
                    g.add(F_iid);
                    g.add(F_return);
                    g.setSize(350,250);
                    g.setLayout(null);
                    g.setVisible(true);
                    g.setLocationRelativeTo(null);              
    }
    });
     
    f.setSize(600,200);
    f.setLayout(null);
    f.setVisible(true);
    f.setLocationRelativeTo(null);
     
    }
}