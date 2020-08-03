package lesson05;

/*
   ФИО:  		  Свищ Владимир Сергеевич
   Факультет:     Geek University Android-разработки
   Курс: 		  Java Core. Профессиональный уровень
   Урок: 		  Урок 5. Многопоточность. Часть II
   Дата правки:   30.07.2020
*/

import java.util.concurrent.*;

public class MainLesson05 {
    /*
    Организуем гонки:

        Все участники должны стартовать одновременно, несмотря на то, что
        на подготовку у каждого их них уходит разное время.
        В тоннель не может заехать одновременно больше половины участников (условность).
        Попробуйте все это синхронизировать.
        Только после того, как все завершат гонку, нужно выдать объявление об окончании.
        Можете корректировать классы (в т.ч. конструктор машин) и добавлять объекты классов
        из пакета util.concurrent.
     */

    public static final int CARS_COUNT = 4;
    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");

        CountDownLatch startLatch = new CountDownLatch(CARS_COUNT);
        CountDownLatch endLatch = new CountDownLatch(CARS_COUNT);
        Race race = new Race(new Start(startLatch),new Road(60), new Tunnel(CARS_COUNT/2), new Road(40),new Finish(endLatch));



        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }

        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }

        try {
            startLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");

        try {
            endLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


//
//        Semaphore smp = new Semaphore(CARS_COUNT/2);
//
//        try {
//            smp.acquire();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            smp.release();
//        }

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}


