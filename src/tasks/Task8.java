package tasks;

import common.Person;
import common.Task;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
А теперь о горьком
Всем придется читать код
А некоторым придется читать код, написанный мною
Сочувствую им
Спасите будущих жертв, и исправьте здесь все, что вам не по душе!
P.S. функции тут разные и рабочие (наверное), но вот их понятность и эффективность страдает (аж пришлось писать комменты)
P.P.S Здесь ваши правки желательно прокомментировать (можно на гитхабе в пулл реквесте)
 */
public class Task8 implements Task {

  private long count;

  //Не хотим выдывать апи нашу фальшивую персону, поэтому конвертим начиная со второй
  /*
   1)Для того, чтобы пропустить первую персону добавила .skip(1)
   2) На мой взгляд, проверка размера входного листа и создание пустого массива здесь ухудшают читаемость.
      В моём решении при пустом входе тоже будет пустой выход.
   */
  public List<String> getNames(List<Person> persons) {

    return persons.stream()
            .skip(1)
            .map(Person::getFirstName)
            .collect(Collectors.toList());
  }

  //ну и различные имена тоже хочется
  /*
  1) Из листа можно создать сет не используя стрим апи
  2) Метод getNames возращает лист -> смотри пункт 1
   */
  public Set<String> getDifferentNames(List<Person> persons) {
    
    return new HashSet<String>(getNames(persons));
  }

  //Для фронтов выдадим полное имя, а то сами не могут
  /*
  Каждый метод вызывался дважды (сначала для проверки на налл, потом для дальнейщей конкатенации строк),
  можно вызвать один раз, потом отфильтровать nonNull.
  Ещё был продублирован вызов метода с фамилией, поменяла на отчество.
   */
  public String convertPersonToString(Person person) {
    return Stream.of(
            person.getSecondName(),
            person.getFirstName(),
            person.getMiddleName())
            .filter(Objects::nonNull)
            .collect(Collectors.joining(" "));
  }

  // словарь id персоны -> ее имя
  public Map<Integer, String> getPersonNames(Collection<Person> persons) {

    return persons.stream()
            .collect(Collectors.toMap(
                    Person::getId,
                    person -> convertPersonToString(person)
            ));
  }

  // есть ли совпадающие в двух коллекциях персоны?
  /*
  https://stackoverflow.com/questions/8708542/something-like-contains-any-for-java-set
   */
  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
    return !Collections.disjoint(persons1, persons2);
  }


  public long countEven(Stream<Integer> numbers) {

    return numbers.filter(num -> num % 2 == 0).count();
  }

  @Override
  public boolean check() {
    System.out.println("Слабо дойти до сюда и исправить Fail этой таски?");
    boolean codeSmellsGood = true;
    boolean reviewerDrunk = false;
    return codeSmellsGood || reviewerDrunk;
  }
}
