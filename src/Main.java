
/*三角形
import java.util.Arrays;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int a= sc.nextInt();
        int [][]b=new int[a+1][a+1];
        b[0][0]=1;
        for (int i=1;i<=a;i++){
            b[i][0]=1;
            for (int j=1;j<=a;j++){
                b[i][j]=b[i-1][j-1]+b[i-1][j];
            }
        }
        System.out.print(Arrays.toString(b[a]));
    }
}*/
/*机器人
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int a= sc.nextInt();
        int b= sc.nextInt();
        int [][]c=new int[a+1][b+1];
        int count=0;
        c[0][0]=1;
        for (int i=1;i<a;i++){
            c[i][0]=1;
            for (int j=1;j<b;j++){
                c[0][j]=1;
                c[i][j]=c[i-1][j]+c[i][j-1];
            }
        }
        System.out.println(c[a-1][b-1]);
    }
}*/

/*硬币
import java.util.Scanner;

public class Main {
    static int []b;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int a= sc.nextInt();
        b=new int[a+1];
        b[0]=0;
        int []c={2,5,7};
        System.out.println(Fanhui(a,c));
    }
    public static int Fanhui(int a,int[]c){
        for (int i=1;i<=a;i++){
            b[i]=Integer.MAX_VALUE;
            for (int j=0;j<c.length;j++){
                if (i>=c[j]&&b[i-c[j]]!=Integer.MAX_VALUE&&b[i-c[j]]+1<b[i]){
                    b[i]=b[i-c[j]]+1;
                }
            }
        }
        if (b[a]==Integer.MAX_VALUE){
            return -1;
        }
        else {
            return b[a];
        }
    }
}*/
/*最大矩形
import java.util.Scanner;

public class Main {
    static int[]b;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        b = new int[a];
        int Max1 =  0;
        for (int i = 0; i < b.length; i++) {
            b[i] = sc.nextInt();
        }
        for (int d = 0; d < b.length; d++) {
            for (int j = d; j < b.length; j++) {
                int count = 0;
                count=Min(d,j)*(j - d+1);
                Max1 = Math.max(Max1, count);
            }
        }

        System.out.println(Max1);
    }
    public static int Min(int i,int j){
        int min=b[i];
        for (int k=i;k<=j;k++){
            if (min>b[k]){
                min=b[k];
            }
        }
        return min;
    }
}*/

/*还钥匙
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int[][] c = new int[b][3];
        for (int i = 0; i < c.length; i++) {
            c[i][0] = scanner.nextInt();
            c[i][1] = scanner.nextInt();
            c[i][2] = scanner.nextInt();
        }
        int[][] d = c;
        int[] F = new int[a];
        for (int i = 0; i < F.length; i++) {
            F[i] = i + 1;
        }
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < F.length; j++) {
                if (F[j] == d[i][0]) {
                    F[j] = 0;
                }
            }
        }
        int[][] e = c;
        for (int i = 0; i < e.length; i++) {
            e[i][1] = e[i][1] + e[i][2];
        }
        for (int i = 0; i < e.length; i++) {
            for (int j = 0; j < e.length; j++) {
                if (e[i][0] == e[j][0] && e[i][1] < e[j][1]&&i<j) {
                    e[i][1] = 999999999;
                }
            }
        }
        Arrays.sort(e, new Comparator<int[]>() {

            @Override
            public int compare(int[] t0, int[] t1) {
                if (t0[1]==t1[1]){
                    return t0[0]-t1[0];
                }
                return t0[1]-t1[1];
            }
        });
        for (int i = 0; i < e.length; i++) {
            for (int j = 0; j < F.length; j++) {
                if (F[j] == 0) {
                    F[j] = e[i][0];
                    break;
                }
            }
        }
        for (int row:F) {
            System.out.print(row + " ");
        }
    }
}*/

/*打酱油
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int count = 0;
        if (a < 30) {
            count = count + a / 10;
        }
        else if (a<50){
            count=count+a/30*4+(a%30)/10;
        }
        else {
            count=count+a/50*7+(a%50)/30*4+((a%50)%30)/10;
        }
        System.out.println(count);
    }
}
*/
