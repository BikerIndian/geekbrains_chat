package lesson06;

/*
   ФИО:  		  Свищ Владимир Сергеевич
   Факультет:     Geek University Android-разработки
   Курс: 		  Java Core. Профессиональный уровень
   Урок: 		  Урок 6. Обзор средств разработки
   Дата правки:   03.08.2020
*/

import java.util.Arrays;

public class MainLesson06 {
    public static void main(String[] args) {
        /*

        1. Написать метод, которому в качестве аргумента передается не пустой одномерный
        целочисленный массив. Метод должен вернуть новый массив, который получен путем
        вытаскивания из исходного массива элементов, идущих после последней четверки.
        Входной массив должен содержать хотя бы одну четверку, иначе в методе необходимо
        выбросить RuntimeException.

        Написать набор тестов для этого метода (по 3-4 варианта входных данных).
        Вх: [ 1 2 4 4 2 3 4 1 7 ] -> вых: [ 1 7 ].

        2. Написать метод, который проверяет состав массива из чисел 1 и 4.
        Если в нем нет хоть одной четверки или единицы, то метод вернет false;
        Написать набор тестов для этого метода (по 3-4 варианта входных данных).

        [ 1 1 1 4 4 1 4 4 ] -> true
        [ 1 1 1 1 1 1 ] -> false
        [ 4 4 4 4 ] -> false
        [ 1 4 4 1 1 4 3 ] -> false

         */
       // int[] arr = {1,2,4,4,2,3,4,1,7};
        int[] arr = {1,4,2,3,3,1,7};
        int[] arr2 = new MainLesson06().getArr(arr);
        for (int i = 0; i <arr2.length ; i++) {
            System.out.println(arr2[i] );
        }


    }

   public int[] getArr(int[] inArr ){

       boolean isFour = false;
       int indexEndFour = 0;
       for (int i = 0; i < inArr.length; i++) {
           if (inArr[i] == 4) {
               isFour = true;
               indexEndFour = i;
           }

       }
       if (!isFour){
           throw new RuntimeException("No four.");
       }

       int[] arrNew = Arrays.copyOfRange(inArr, indexEndFour+1, inArr.length);
       
       return arrNew;
   }
}
