
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Calendar;

public class Main {
    public static void main(String[] args) {

        Abc myObj= new Abc();
    }
}
class Abc extends JFrame implements ActionListener {
    //url,username,password for connection to database
    String url="jdbc:mysql://localhost:3306/bank_application";
    String user="root";
    String password="Kithm!234";
    JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,l16;
    JPasswordField pas1,pas2,pas3;
    int ac=245;;

    JProgressBar p;
    JTextField t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11;
    JButton b1,b2,b3,b4,b5;
    Timer t;
    int i;

   Abc(){
       //Initialize All Elements in the First Window
       l1= new JLabel("Welcome To the Commercial Bank");

       p= new JProgressBar(0,30);

        //Create panel Object for Line Breaking
       JPanel panel= new JPanel();
       panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

       panel.add(l1);
       panel.add(Box.createRigidArea(new Dimension(0,200)));
       panel.add(p);

        //Add Element into the Frame
       add(panel);

       //Create Time object for Loading to next Frame
        t= new Timer(250,this);
        t.start();




        setLocation(400,200);
       //All Element are Adjust for Center
        setLayout(new FlowLayout());
        //Frame must be Visible
        setVisible(true);
        //Adjust Frame Size
        setSize(400,400);
        //Set Close Operation When Click the Close Button
        setDefaultCloseOperation(3);
    }

    public void actionPerformed(ActionEvent e){
       //Set Value to Progressbar every 250 seconds
           p.setValue(i);
           if(i==30){
               //After i becomes 30 the New Frame will be Load
               JFrame fr= new JFrame();
               fr.setLocation(400,200);
               dispose();

            //Initialize All Element in the 2 Frame
               l2=new JLabel("Enter Your Id Card Number : ");
               t1= new JTextField(20);


               l3=new JLabel("Enter Your Password : ");
               pas3= new JPasswordField(20);
               pas3.setEchoChar('*');




               b1= new JButton("Submit");
               b4=new JButton("see password");


               l4= new JLabel("You Haven't Account? ");
               b2= new JButton("Click here");
               l1=new JLabel(".");
               l14=new JLabel(".");
               l10=new JLabel(".");


               JPanel panel= new JPanel();

               panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

               panel.add(l2);
               panel.add(t1);
               panel.add(Box.createRigidArea(new Dimension(0,15)));

               panel.add(l3);

               panel.add(pas3);
               panel.add(Box.createRigidArea(new Dimension(0,20)));

               panel.add(b1);
               panel.add(Box.createRigidArea(new Dimension(0,20)));
               panel.add(l10);

               panel.add(l4);
               panel.add(b2);
               panel.add(l1);
               panel.add(l14);


               //When the user try to log in the Application
               b1.addActionListener(new ActionListener(){
                   public void actionPerformed(ActionEvent a){
                       try{
                           Connection con=DriverManager.getConnection(url,user,password);
                           Statement st= con.createStatement();
                           String query="select password from user_details where id_card='"+t1.getText()+"';";

                           ResultSet res= st.executeQuery(query);
                           String pas="";

                           while(res.next()){
                                pas=res.getString("password");

                           }
                           char[] pasword= pas3.getPassword();
                           String password2=new String(pasword);



                           if(password2.equals(pas)){
                               //new Frame
                               JFrame fr3= new JFrame();
                               fr3.setLocation(400,200);
                               java.util.Date dt= new java.util.Date();

                               String sql="insert into login_details values('"+t1.getText()+"','"+dt.toString()+"','pending');";
                               int re=st.executeUpdate(sql);

                                //After Come new Frame the old Frame is close automatically
                               fr.dispose();
                               //Initialize all Element in the new Frame
                               b1= new JButton("Transaction");
                               b2= new JButton("My Bank Account Information");
                               b3= new JButton("Login Information");
                               b5=new JButton("Log Out");

                               b3.addActionListener(new ActionListener() {
                                   @Override
                                   public void actionPerformed(ActionEvent e) {
                                       try{
                                           JFrame f8= new JFrame();
                                           f8.setLocation(400,200);
                                           fr3.dispose();

                                           b3= new JButton("Back");

                                           DefaultListModel<String> de= new DefaultListModel<>();

                                           Connection con= DriverManager.getConnection(url,user,password);
                                           Statement st = con.createStatement();

                                           String sql="select * from login_details where  id_number='"+t1.getText()+"';";
                                           ResultSet res= st.executeQuery(sql);

                                           while(res.next()){
                                               String id=res.getString("id_number");
                                               String logindate=res.getString("login_date");
                                               String logout=res.getString("logout_date");
                                               de.addElement(logindate+"          "+logout);

                                           }
                                           JList<String> lists= new JList<>(de);

                                           JPanel panel= new JPanel();

                                           panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));;

                                           panel.add(lists);
                                           panel.add(Box.createRigidArea(new Dimension(0,15)));
                                           panel.add(b3);
                                           f8.add(panel);

                                           b3.addActionListener(new ActionListener() {
                                               @Override
                                               public void actionPerformed(ActionEvent e) {
                                                   fr3.setVisible(true);
                                                   f8.dispose();
                                               }
                                           });

                                           f8.setVisible(true);
                                           f8.setSize(400,400);
                                           f8.setLayout(new FlowLayout());
                                           f8.setDefaultCloseOperation(3);

                                           con.close();
                                           st.close();

                                       }catch (SQLException e6){
                                           e6.printStackTrace();
                                       }


                                   }
                               });

                               l14=new JLabel(".");

                               JPanel panel = new JPanel();
                               panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

                               panel.add(b2);
                               panel.add(Box.createRigidArea(new Dimension(0,80)));

                               panel.add(b1);
                               panel.add(Box.createRigidArea(new Dimension(0,80)));

                               panel.add(b3);
                               panel.add(Box.createRigidArea(new Dimension(0,80)));

                               panel.add(b5);
                               b5.addActionListener(new ActionListener() {
                                   @Override
                                   public void actionPerformed(ActionEvent e) {
                                       fr.setVisible(true);
                                       try{
                                           Connection con = DriverManager.getConnection(url,user,password);
                                           Statement st = con.createStatement();

                                           java.util.Date dt = new java.util.Date();

                                           String sql="update login_details set logout_date='"+dt.toString()+"' where id_number='"+t1.getText()+"' && logout_date='pending';";
                                           int res= st.executeUpdate(sql);
                                           fr3.dispose();

                                       }catch (SQLException e5){
                                           e5.printStackTrace();

                                       }
                                   }
                               });

                                b1.addActionListener(new ActionListener(){
                                    public void actionPerformed(ActionEvent e){
                                        JFrame fr6 = new JFrame();
                                        fr6.setLocation(400,200);
                                        l11=new JLabel("Bank to Bank Transactions");
                                        l12=new JLabel("Enter Your Account Number :");
                                        l13=new JLabel("Enter Other Destination Account Number");
                                        l14=new JLabel("Enter Amount(Rs.)");
                                        l15=new JLabel(".");
                                        l16=new JLabel(".");

                                        b5=new JButton("Submit");
                                        b2=new JButton("Details About Transaction");
                                        b4=new JButton("Back");

                                        t2=new JTextField(15);
                                        t3=new JTextField(15);
                                        t4=new JTextField(15);

                                        JPanel panel= new JPanel();

                                        b2.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                JFrame fr7 = new JFrame();
                                                fr7.setLocation(400,200);
                                                b3= new JButton("Back");
                                                l14=new JLabel(".");
                                                fr6.dispose();
                                                String sql="select * from transaction_details where id_number='"+t1.getText()+"';";
                                                try{
                                                    Connection con= DriverManager.getConnection(url,user,password);
                                                    Statement st= con.createStatement();
                                                    ResultSet re4=st.executeQuery(sql);

                                                    DefaultListModel<String> n1= new DefaultListModel<>();
                                                    n1.addElement("Your Account Number  "+" Destination Account Number    "+"Your Id Number             "+"       Date     "+"                                    Amount(Rs.)");
                                                    n1.addElement("     ");
                                                    while (re4.next()){
                                                        String acc_number=re4.getString("account_number");
                                                        String id_number=re4.getString("id_number");
                                                        String date=re4.getString("date");
                                                        int amount=re4.getInt("amount");
                                                        String des_number=re4.getString("dest_acc_number");
                                                        n1.addElement(acc_number+"       "+des_number+"           "+id_number+"          "+date+"      Rs."+amount);
                                                        n1.addElement("  ");

                                                    }
                                                    JList<String> lists= new JList<>(n1);
                                                    JPanel panel= new JPanel();
                                                    panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

                                                    panel.add(lists);
                                                    panel.add(Box.createRigidArea(new Dimension(0,15)));
                                                    panel.add(b3);
                                                    panel.add(l14);



                                                    b3.addActionListener(new ActionListener() {
                                                        @Override
                                                        public void actionPerformed(ActionEvent e) {
                                                            fr6.setVisible(true);
                                                            fr7.dispose();
                                                        }
                                                    });

                                                    fr7.add(panel);

                                                    con.close();
                                                    st.close();
                                                }catch (SQLException e4){
                                                    e4.printStackTrace();
                                                }


                                                fr7.setVisible(true);
                                                fr7.setSize(700,400);
                                                fr7.setLayout(new FlowLayout());
                                                fr7.setDefaultCloseOperation(3);
                                            }
                                        });
                                        b4.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                fr3.setVisible(true);
                                                fr6.dispose();
                                            }
                                        });

                                        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
                                        panel.add(l11);
                                        panel.add(Box.createRigidArea(new Dimension(0,15)));

                                        panel.add(l12);
                                        panel.add(t2);
                                        panel.add(Box.createRigidArea(new Dimension(0,15)));

                                        panel.add(l13);
                                        panel.add(t3);
                                        panel.add(Box.createRigidArea(new Dimension(0,15)));

                                        panel.add(l14);
                                        panel.add(t4);
                                        panel.add(Box.createRigidArea(new Dimension(0,15)));

                                        panel.add(b5);
                                        panel.add(Box.createRigidArea((new Dimension(0,15))));
                                        panel.add(l15);

                                        panel.add(b2);
                                        panel.add(Box.createRigidArea((new Dimension(0,15))));

                                        panel.add(b4);
                                        panel.add(l16);

                                        fr6.add(panel);

                                        b5.addActionListener(new ActionListener() {
                                            public void actionPerformed(ActionEvent e){
                                                try{



                                                    if (t2.getText().equals("")|| t3.getText().equals("")||t4.getText().equals("")) {
                                                        l15.setText("Please Fill all the Details");

                                                    }else{
                                                        if(t2.getText().length()==17 && t3.getText().length()==17){

                                                            Connection con =DriverManager.getConnection(url,user,password);
                                                            Statement st=con.createStatement();
                                                            java.util.Date dt= new java.util.Date();

                                                            String sql2="select card_types from card_types where account_number='"+t2.getText()+"';";
                                                            ResultSet re= st.executeQuery(sql2);

                                                            String getCardType="";
                                                            while(re.next()){
                                                                getCardType=re.getString("card_types");
                                                            }




                                                            if(getCardType.equals("Saving Account")){
                                                                String sql3="select money from card_types where account_number='"+t2.getText()+"';";
                                                                ResultSet re2=st.executeQuery(sql3);

                                                                int money=0;
                                                                while(re2.next()){
                                                                    money=re2.getInt("money");
                                                                }
                                                                if(money < Integer.parseInt(t4.getText())){
                                                                    l15.setText("You Applied Amount is to bigger than Your Account Money ");
                                                                }else{
                                                                    String sql4="update card_types set money=(money-"+t4.getText()+") where account_number='"+t2.getText()+"' && id_card='"+t1.getText()+"';";
                                                                    String sql5="update card_types set money=(money+"+t4.getText()+") where account_number='"+t3.getText()+"';";

                                                                    int result= st.executeUpdate(sql4);
                                                                    int result1= st.executeUpdate(sql5);

                                                                    if(result==1){
                                                                        if(result1==1){
                                                                            String sql="insert into transaction_details (account_number,id_number,date,amount,dest_acc_number) values " +
                                                                                    "('"+t2.getText()+"','"+t1.getText()+"','"+dt.toString()+"',"+t4.getText()+",'"+t3.getText()+"');";

                                                                            int res= st.executeUpdate(sql);
                                                                            if(res==1){

                                                                                l15.setText("Successfully Filled");
                                                                            }else{
                                                                                l15.setText("Please Enter Right Account Numbers");
                                                                            }

                                                                        }


                                                                    }else{
                                                                        l15.setText("Please Enter Right Account Numbers");
                                                                    }

                                                                }

                                                            }else{
                                                                String sql3="select interest_only_fd from card_types where account_number='"+t2.getText()+"';";
                                                                ResultSet re2=st.executeQuery(sql3);

                                                                int money=0;
                                                                while(re2.next()){
                                                                    money=re2.getInt("interest_only_fd");
                                                                }
                                                                if(money < Integer.parseInt(t4.getText())){
                                                                    l15.setText("You Applied Amount is to bigger than Your Account Moneyss ");
                                                                }else{
                                                                    String sql4="update card_types set interest_only_fd=(interest_only_fd-"+t4.getText()+") where account_number='"+t2.getText()+"' && id_card='"+t1.getText()+"';";
                                                                    String sql5="update card_types set money=(money+"+t4.getText()+") where account_number='"+t3.getText()+"';";

                                                                    int result= st.executeUpdate(sql4);
                                                                    int result1= st.executeUpdate(sql5);

                                                                    if(result==1){
                                                                        if(result1==1){
                                                                            String sql="insert into transaction_details (account_number,id_number,date,amount,dest_acc_number) values " +
                                                                                    "('"+t2.getText()+"','"+t1.getText()+"','"+dt.toString()+"',"+t4.getText()+",'"+t3.getText()+"');";

                                                                            int res= st.executeUpdate(sql);
                                                                            if(res==1){

                                                                                l15.setText("Successfully Filled");
                                                                            }else{
                                                                                l15.setText("Please Enter Right Account Numbers");
                                                                            }
                                                                        }else {
                                                                            l15.setText("Please Enter Right Account Number");
                                                                        }


                                                                    }else{
                                                                        l15.setText("Please Enter Right Account Numbers");
                                                                    }

                                                                }

                                                            }

                                                        }else{
                                                            l15.setText("Please Check the Additional Spaces and Account Number");
                                                        }





                                                    }
                                                    con.close();
                                                    st.close();

                                                }catch (SQLException e3){
                                                    e3.printStackTrace();
                                                }

                                            }
                                        });
                                        fr3.dispose();
                                        fr6.setVisible(true);
                                        fr6.setSize(400,400);
                                        fr6.setLayout(new FlowLayout());
                                        fr6.setDefaultCloseOperation(3);
                                    }
                                });
                               panel.add(l14);
                               fr3.add(panel);

                               b2.addActionListener((new ActionListener() {
                                   @Override
                                   public void actionPerformed(ActionEvent e) {
                                       JFrame fr4= new JFrame();
                                       fr4.setLocation(400,200);
                                       fr3.dispose();
                                       try{
                                           Connection con=DriverManager.getConnection(url,user,password);
                                           Statement st=con.createStatement();

                                           l12=new JLabel(".");
                                           l13= new JLabel(".");
                                           b4=new JButton("Request New Account");
                                           b5=new JButton("Back");
                                           String[] acc_number=new String[10];
                                           int[] money1= new int[10];

                                           String[] fix=new String[10];
                                           int[] money2= new int[10];



                                           JPanel panel = new JPanel();

                                           panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
                                           fr4.add(panel);


                                           String sql="select account_number,money,card_types,created_year,created_month,interest_only_fd from card_types where id_card='"+t1.getText()+"';";
                                           ResultSet res= st.executeQuery(sql);
                                           //Use indexes
                                           int j=0;
                                           int w=0;
                                           int[] getYear=new int[10];
                                           int[] getMonth=new int[10];
                                           int[] interest=new int[10];

                                           while (res.next()){
                                               String card_number=res.getString("account_number");
                                                int money=res.getInt("money");
                                                String card_type=res.getString("card_types");
                                                 int getYear1=res.getInt("created_year");
                                                 int getMonth1=res.getInt("created_month");
                                                 int getInter=res.getInt("interest_only_fd");

                                                if(card_type.equals("Fixed Account")){
                                                    getYear[w]=getYear1;
                                                    getMonth[w]=getMonth1;
                                                    interest[w]=getInter;

                                                    fix[w]=card_number+"  "+card_type;
                                                    money2[w]=money;

                                                    w++;
                                                    continue;
                                                }


                                                acc_number[j]=card_number+"   "+card_type;
                                                money1[j]=money;
                                                j++;

                                           }


                                           String[] mon= new String[money1.length];
                                           String[] mon1= new String[money1.length];

                                           for(int k=0;k < money1.length;k++){
                                               if(money1[k]==0){
                                                   break;
                                               }
                                               if(k==0){
                                                   for (int y=0;y < money2.length;y++){
                                                       mon1[y]=money2[y]+"";
                                                   }
                                               }
                                               String item  = money1[k]+"";
                                               mon[k]=item;

                                           }

                                           DefaultListModel<String> li= new DefaultListModel<>();
                                           li.addElement("Account Number  "+"          Card Type  "+"         Amount");
                                           li.addElement(".               "+"                  .  "+"         .     ");
                                           DefaultListModel<String> l1= new DefaultListModel<>();


                                           Calendar cl = Calendar.getInstance();
                                           int year= cl.get(Calendar.YEAR);
                                           int month=cl.get(Calendar.MONTH);


                                           for(int k=0;k < getYear.length;k++){
                                               if(getYear[k] < year){
                                                   if(getMonth[k] < month){
                                                       if(getYear[k]==0){
                                                           break;
                                                       }
                                                       int interest1=(money2[k]*8)/100;
                                                       int holder= interest[k]+interest1;
                                                       String sql1="update card_types set interest_only_fd="+holder+" where id_card='"+t1.getText()+"' && account_number='"+fix[k].substring(0,17)+"';";
                                                       int rs=st.executeUpdate(sql1);

                                                       if(rs==0){
                                                           l12.setText("Error Occured");

                                                       }

                                                   }

                                               }
                                           }




                                           for(int k=0;k < acc_number.length;k++){
                                               if(acc_number[k]==null){
                                                   break;
                                               }
                                               li.addElement(acc_number[k]+"    Rs."+mon[k]);
                                           }

                                           String sql3="select account_number,card_types,money,interest_only_fd from card_types where card_types='Fixed Account';";
                                           ResultSet res1=st.executeQuery(sql3);

                                           l1.addElement("Account Number"+"  "+"            Card Type   "+"    Initial Amount   "+" "+"  Interest");
                                           l1.addElement("               "+"  "+"                        "+"                    "+" "+"          ");
                                           while(res1.next()){
                                               String getac=res1.getString("account_number");
                                               String getCar=res1.getString("card_types");
                                               int getAmount=res1.getInt("money");
                                               int getInterest=res1.getInt("interest_only_fd");
                                               l1.addElement(getac+"    "+"  "+getCar+"     "+getAmount+"        "+" "+getInterest );
                                           }



                                           JList<String> li1=new JList<>(li);
                                           JList<String> li2=new JList<>(l1);

                                           li1.setSize(100,50);

                                           panel.add(li1);
                                           panel.add(Box.createRigidArea(new Dimension(0,15)));
                                           panel.add(li2);
                                           panel.add(Box.createRigidArea(new Dimension(0,20)));
                                           panel.add(b4);
                                           panel.add(Box.createRigidArea(new Dimension(0,20)));
                                           panel.add(b5);

                                           con.close();
                                           st.close();





                                       }catch (SQLException e1){
                                           e1.printStackTrace();
                                       }
                                       b5.addActionListener(new ActionListener() {
                                           @Override
                                           public void actionPerformed(ActionEvent e) {
                                               fr4.dispose();
                                               fr3.setVisible(true);
                                           }
                                       });
                                       b4.addActionListener(new ActionListener(){
                                           public void actionPerformed(ActionEvent e){
                                               //Create e new Frame
                                               JFrame fr5 = new JFrame();
                                               fr5.setLocation(400,200);

                                               b1=new JButton("Back");
                                               b2=new JButton("Submit");
                                               t10= new JTextField(15);
                                               String[] list={"Saving Accounting"};
                                               JComboBox<String> com= new JComboBox<>(list);
                                               l9=new JLabel("Enter Your Id Number :");
                                               l10=new JLabel(" Card Types");
                                               l11=new JLabel(".");
                                               fr4.dispose();
                                               JPanel panel= new JPanel();

                                               panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

                                               panel.add(l9);
                                               panel.add(t10);
                                               panel.add(Box.createRigidArea(new Dimension(0,15)));
                                               panel.add(l10);
                                               panel.add(com);
                                               panel.add(Box.createRigidArea(new Dimension(0,15)));
                                               panel.add(b2);
                                               panel.add(Box.createRigidArea(new Dimension(0,15)));
                                               panel.add(l11);
                                               panel.add(Box.createRigidArea(new Dimension(0,15)));
                                               panel.add(b1);
                                               panel.add(Box.createRigidArea(new Dimension(0,15)));

                                               b1.addActionListener(new ActionListener() {
                                                   @Override
                                                   public void actionPerformed(ActionEvent e) {
                                                       fr4.setVisible(true);
                                                       fr5.dispose();
                                                   }
                                               });
                                               b2.addActionListener(new ActionListener() {
                                                   @Override
                                                   public void actionPerformed(ActionEvent e) {
                                                       if(!(t1.getText().equals(t10.getText()))){
                                                           l11.setText("Your Id Number is Wrong");
                                                       }else{
                                                           try{
                                                               Connection con =DriverManager.getConnection(url,user,password);
                                                               Statement st = con.createStatement();
                                                               ac++;
                                                               String ac_nu="00"+ac+t10.getText();
                                                               Calendar cl=Calendar.getInstance();
                                                               int year=cl.get(Calendar.YEAR);
                                                               int month=cl.get(Calendar.MONTH);
                                                               String sql="insert into card_types(id_card,card_types,money,account_number,created_year,created_month,interest_only_fd) values('"+t10.getText()+"','"+com.getSelectedItem()+"',"+0+",'"+ac_nu+"',"+year+","+month+","+0+");";
                                                               int result=st.executeUpdate(sql);
                                                               if(result==1){
                                                                   l11.setText("Successfully Created");

                                                               }else{
                                                                   l11.setText("Something Went Wrong");
                                                               }


                                                           }catch (SQLException e2){
                                                               e2.printStackTrace();
                                                           }

                                                       }

                                                   }
                                               });



                                               fr5.add(panel);


                                               b1.addActionListener(new ActionListener() {
                                                   @Override
                                                   public void actionPerformed(ActionEvent e) {
                                                       fr4.setVisible(true);
                                                   }
                                               });
                                               fr5.setVisible(true);
                                               fr5.setLayout(new FlowLayout());
                                               fr5.setSize(400,400);
                                               fr5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                                           }
                                       });




                                       fr4.setLayout(new FlowLayout());
                                       fr4.setVisible(true);
                                       fr4.setSize(400,400);
                                       fr4.setDefaultCloseOperation(3);

                                   }
                               }));

                               fr3.setLayout(new FlowLayout());
                               fr3.setSize(400,400);
                               fr3.setVisible(true);
                               fr3.setDefaultCloseOperation(3);
                           }else{
                               l10.setText("Login Details Wrong , Please check Again. ");
                           }
                           con.close();
                           st.close();
                       }catch(SQLException e){
                           e.printStackTrace();
                       }
                   }
               });

               b2.addActionListener(new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent e) {
                       JFrame fr2 = new JFrame();
                       fr2.setLocation(400,200);
                       fr.dispose();

                       //Initialize all Text Fields for Frame 3

                       t3=new JTextField(15);
                       t4=new JTextField(15);
                       t5=new JTextField(15);
                       t6=new JTextField(15);
                       t7=new JTextField(15);
                       t8=new JTextField(15);
                       t9=new JTextField(15);
                       pas1=new JPasswordField(15);
                       pas2=new JPasswordField(15);

                       pas1.setEchoChar('*');
                       pas2.setEchoChar('*');







                       //Initialize all Labels for Frame 3
                       l5= new JLabel("Enter Your First Name : ");
                       l6= new JLabel("Enter Your Last Name : ");
                       l7= new JLabel("Enter Your Identity Card Number : ");
                       l8= new JLabel("Enter Your BirthDay (Use this Style yyyy/mm/dd): ");
                       l9= new JLabel("Enter Your Address City : ");
                       l10= new JLabel("Enter Your Gmail : ");
                       l11= new JLabel("Enter Your Phone Number : ");
                       l12= new JLabel("Create a Password : ");
                       l13= new JLabel("Enter Password Again : ");

                       l15=new JLabel("Enter Account Type :");
                       l16= new JLabel("Your Details Form");

                       String[] items={"Saving Account"};
                       JComboBox<String> list= new JComboBox<>(items);






                       //Initialize all Buttons for Frame 3
                       b3= new JButton("Submit");

                       JPanel panel = new JPanel();

                       panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

                       panel.add(l16);
                       panel.add(Box.createRigidArea(new Dimension(0,10)));

                       panel.add(l5);
                       panel.add(t3);
                       panel.add(Box.createRigidArea(new Dimension(0,10)));


                       panel.add(l6);
                       panel.add(t4);
                       panel.add(Box.createRigidArea(new Dimension(0,10)));


                       panel.add(l7);
                       panel.add(t5);
                       panel.add(Box.createRigidArea(new Dimension(0,10)));


                       panel.add(l8);
                       panel.add(t6);
                       panel.add(Box.createRigidArea(new Dimension(0,10)));


                       panel.add(l9);
                       panel.add(t7);
                       panel.add(Box.createRigidArea(new Dimension(0,10)));


                       panel.add(l10);
                       panel.add(t8);
                       panel.add(Box.createRigidArea(new Dimension(0,10)));


                       panel.add(l11);
                       panel.add(t9);
                       panel.add(Box.createRigidArea(new Dimension(0,10)));

                       panel.add(l15);
                       panel.add(list);
                       panel.add(Box.createRigidArea(new Dimension(0,10)));


                       panel.add(l12);
                       panel.add(pas1);
                       panel.add(Box.createRigidArea(new Dimension(0,10)));

                       panel.add(l13);
                       panel.add(pas2);
                       panel.add(Box.createRigidArea(new Dimension(0,10)));

                       panel.add(b3);



                       fr2.add(panel);
                       b3.addActionListener(new ActionListener() {
                           @Override
                           public void actionPerformed(ActionEvent e) {

                               String gmail=t8.getText();
                               int holder=0;
                               for(int j=0;j<gmail.length();j++){
                                   if(gmail.charAt(j)=='@'){
                                       holder++;
                                   }
                               }

                               char[] pas= pas1.getPassword();
                               String passie= new String(pas);

                               char[] pas_01= pas2.getPassword();
                               String passie1= new String(pas_01);







                               if(t3.getText().equals("") || t4.getText().equals("") || t5.getText().equals("") || t6.getText().equals("") || t7.getText().equals("")  || t8.getText().equals("") || t9.getText().equals("") ||
                                       passie.equals("") || passie1.equals("")){
                                   l14.setText("Please Fill the All Boxes");
                               }
                               if(holder==0 || (t9.getText().length()!=10) || (!passie.equals(passie1))){
                                   l1.setText("(Please Enter Information Rightly. Please Fill the Form Again.)");

                                   fr2.dispose();
                                   fr.setVisible(true);
                               }else{
                                   //After sucessfully filled the all the details of that user will store our database
                                   //so first connect to the database

                                   try{


                                       Connection con = DriverManager.getConnection(url,user,password);
                                       //get Input Texes
                                       ac++;
                                       //Calculate Account Number
                                       String acc_number="00"+ac+t5.getText();
                                       Calendar cl= Calendar.getInstance();
                                       int year=cl.get(Calendar.YEAR);
                                       int month= cl.get(Calendar.MONTH);


                                       
                                       //Insert query for store user details table in database;
                                       String query="insert into user_details (First_name,Last_name,id_Card,birthday,address,gmail,phone_number,password)" +
                                               "values ('"+t3.getText()+"','"+t4.getText()+"','"+t5.getText()+"','"+t6.getText()+"','"+t7.getText()+"','"+t8.getText()+"','"+t9.getText()+"','"+passie1+"');";

                                       String query2="insert into card_types (id_card,card_types,money,account_number,created_year,created_month,interest_only_fd)values('"+t5.getText()+"','"+list.getSelectedItem()+"',"+0+",'"+acc_number+"',"+year+","+month+",0);";
                                       Statement st=con.createStatement();
                                       int res= st.executeUpdate(query);
                                       int res1=st.executeUpdate(query2);

                                       if(res==1 && res1==1){
                                           l1.setText("Successfully Filled,Your Bank Account Number : "+acc_number);

                                           fr2.dispose();
                                           fr.setVisible(true);
                                           con.close();
                                           st.close();


                                       }

                                       con.close();
                                       st.close();
                                       


                                   }catch (SQLException e1){
                                       e1.printStackTrace();
                                   }

                               }

                           }
                       });




                       fr2.setLayout(new FlowLayout());
                       fr2.setSize(400,650);
                       fr2.setVisible(true);
                       fr2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                   }
               });

               fr.add(panel);


               //Use for all element should be Center
               fr.setLayout(new FlowLayout());
               //Visible of new Frame 2
               fr.setVisible(true);
               //set some sizes for  Frame 2
               fr.setSize(550,400);
               fr.setDefaultCloseOperation(3);

           }
           i++;


    }
}