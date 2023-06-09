import java.util.HashMap;
import java.util.Map;

public class demo {
    public static void main(String args[]) {
        int a[] = {0, 1, 2, 0, 4, 6, 1};
        Map<Integer, Integer> count = countNumbers(a);
        System.out.println(count);
    }
        public static Map<Integer,Integer> countNumbers(int[] arr){
        Map<Integer,Integer> count =new HashMap<>();
        for (int num:arr){
            if(count.containsKey(num)) {
                count.put(num, count.get(num) + 1);
            }
            else{
                count.put(num,1);
                }
        }
        return count;
        }




//    int count=0;
//    for(int i=0;i<a.length;i++){
//        if(a[i]==0){
//            count++;
//        }
//    }
//    System.out.println("o are repeted "+count+"times");

    }

