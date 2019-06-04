package per.yyu.DeviceManageHelper;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int helperAction = 0;
        int doorayAction = 0;
        int days = 0;
        String holdPeriod = "";
        String longRemain = "";

        HelperAPI helperAPI = new HelperAPI();
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("원하시는 작업을 선택하세요 (exit = 936)");
            System.out.println("1. 디바이스 미반납자 검색");
            System.out.println("2. n일 간 대여/반납 기록이 없는 디바이스 검색");
            System.out.println("3. 장기대여 반납 n일 전인 디바이스 검색");
            helperAction = scanner.nextInt();

            switch(helperAction) {
                case 1 :
                    helperAPI.overdue_Begin();

                    System.out.println("\n두레이로 전송하시겠습니까 ?");
                    System.out.println("1. Yes");
                    System.out.println("2. No");
                    doorayAction = scanner.nextInt();

                    switch(doorayAction) {
                        case 1 :
                            helperAPI.send_To_Dooray_Overdue();
                            break;

                        case 2 :
                            break;
                    }

                    helperAPI.overdue_Finish();

                    break;

                case 2 :
                    System.out.println("며칠동안 기록이 없는 디바이스를 찾을까요 ?");
                    days = scanner.nextInt();

                    holdPeriod = String.valueOf(days);

                    helperAPI.hold_Begin(holdPeriod);

                    System.out.println("\n두레이로 전송하시겠습니까 ?");
                    System.out.println("1. Yes");
                    System.out.println("2. No");
                    doorayAction = scanner.nextInt();

                    switch(doorayAction) {
                        case 1 :
                            helperAPI.send_To_Dooray_Hold();
                            break;

                        case 2 :
                            break;
                    }

                    helperAPI.hold_Finish();

                    break;

                case 3 :
                    System.out.println("반납까지 며칠남은 디바이스를 찾을까요 ?");
                    days = scanner.nextInt();

                    longRemain = String.valueOf(days);

                    helperAPI.longRent_Begin(longRemain);

                    System.out.println("\n두레이로 전송하시겠습니까 ?");
                    System.out.println("1. Yes");
                    System.out.println("2. No");
                    doorayAction = scanner.nextInt();

                    switch(doorayAction) {
                        case 1 :
                            helperAPI.send_To_Dooray_LongRent();
                            break;

                        case 2 :
                            break;
                    }

                    helperAPI.longRent_Finish();
            }

            if(helperAction == 936) {
                break;
            }
        }

        scanner.close();
    }
}
