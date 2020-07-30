package lesson04;

/*
   ФИО:  		  Свищ Владимир Сергеевич
   Факультет:     Geek University Android-разработки
   Курс: 		  Java Core. Профессиональный уровень
   Урок: 		  Урок 4. Многопоточность. Часть I
   Дата правки:   26.07.2020
*/

public class MainLesson04 {

    public static void main(String[] args) {

        /*
        1. Создать три потока, каждый из которых выводит
           определенную букву (A, B и C) 5 раз (порядок – ABСABСABС).
           Используйте wait/notify/notifyAll.
         */

        ThreadPrintLIne threadPrintLIne = new ThreadPrintLIne();
        threadPrintLIne.add("A ");
        threadPrintLIne.add("B ");
        threadPrintLIne.add("C \n");
        threadPrintLIne.print(5);
    }
}
