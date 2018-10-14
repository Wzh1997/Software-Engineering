
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        Scanner scanner2=new Scanner(System.in);
        String str2=scanner2.nextLine();
        System.out.print(isNumer(str2));


        System.out.println("输入你要的题目数：");
        Scanner scanner=new Scanner(System.in);
        int index=scanner.nextInt();//获取用户输入的题目数

        int dui=0;//对题计数变量
        int cuo=0;//错题计数变量

        for (int i=0;i<index;i++){

            String[] timu=chuti();//将题目存入timu字符串数组中
            float deshu=jisuan(timu);


            if (deshu<0){
                i--;
            }
            else {
                //输出题目
                for (int j=0;j<5;j++){
                System.out.print(timu[j]);
                }
                System.out.print("=");

                //获取用户输入答案
                Scanner scanner1=new Scanner(System.in);
                String str=scanner1.nextLine();

                //判断用户对错
                int duicuo=panduan(deshu,str);
                //对题目对错进行计数
                if (duicuo==1){
                    dui++;
                }else {
                    cuo++;
                }
            }

        }

        System.out.print("对题数："+dui+","+"错题数:"+cuo);




    }





    //出题，每次出一道
    public static String[] chuti(){

        Random random=new Random();

        String[] fuhao=new String[4];
        fuhao[0]="+";
        fuhao[1]="-";
        fuhao[2]="*";
        fuhao[3]="/";


        String[] timu=new String[10];

        for (int i=0;i<5;i++){
            if (i%2==0){
                timu[i]= String.valueOf((random.nextInt(100)+1));//将随机数转为字符串，存入字符串数组
            }else {
                timu[i]=fuhao[random.nextInt(4)];//将符号存入数组
            }
        }

        return timu;

    }

    //计算题目，得出的数
    public static float jisuan(String[] timu){

        float[] shu=new float[10];//用于存操作数的数组
        String[] fu=new String[10];//用于存操作符的数组
        int shu_top=-1;//操作数数组尾部标记
        int fu_top=-1;//操作符数组尾部标记




        for (int i=0;i<5;i++){
            if(isNumer(timu[i])){//判断timu[i]是否为数字
                shu_top++;
                //将timu[i]转为整型，存入数组中
                shu[shu_top]=Integer.valueOf(timu[i]).intValue();
            }
            else if (timu[i]=="*"){//如果是*号，将操作数数组尾部的一个数取出，与*号后的数字相乘，的数再放入操作数数组

                float a=shu[shu_top];
                shu_top--;

                float b=Integer.valueOf(timu[i+1]).intValue();

                shu_top++;
                shu[shu_top]=a*b;

                i++;


            }
            else if (timu[i]=="/"){//如果是/号，将操作数数组尾部的一个数取出，与*号后的数字相除，的数再放入操作数数组
                float a=shu[shu_top];
                shu_top--;

                float b=Integer.valueOf(timu[i+1]).intValue();

                shu_top++;
                shu[shu_top]=a/b;

                i++;
            }
            else {//将操作符放入数组
                fu_top++;
                fu[fu_top]=timu[i];
            }
        }


        //不断将操作数数组中的前两个数取出，将一个操作符取出，进行计算
        //得数放到第二个数的位置，操作符数组为空，则操作数中的数为答案
        int i=0;
        int j=0;
        while (i!=shu_top){
            float a=shu[i];
            float b=shu[i+1];

            String fuhao=fu[j];
            if (fuhao=="+"){
                shu[i+1]=a+b;
            }else {
                shu[i+1]=a-b;
            }

            i=i+1;
            j=j+1;
        }


        return shu[shu_top];
    }


    //判断用户输入答案是否正确
    public static int panduan(float deshu,String str){


        float temp=0;

        //判断用户输入是否是分数，做相应计算
        int i=str.indexOf("/");//找到‘/’在str中的位置，没有则返回-1
        if (i!=-1){
            String str_a=str.substring(0,i);//将/号前面的数字取出
            String str_b=str.substring(i+1);//将/号后面的数字取出
            float a=Integer.valueOf(str_a).intValue();
            float b=Integer.valueOf(str_b).intValue();
             temp=a/b;
        }else {
             temp=Integer.valueOf(str).intValue();
        }

        if (temp==deshu){
            System.out.print("对\n");
            return 1;

        }else {
            System.out.print("错\n");
            return 0;
        }
    }

    //判断字符串是否都为数字,小数不能判断
    public static boolean isNumer(String str) {

        Pattern pattern=Pattern.compile("[0-9]*");
        Matcher isNum=pattern.matcher(str);
        if( !isNum.matches() )
        {
            return false;
        }
        return true;
    }

}
