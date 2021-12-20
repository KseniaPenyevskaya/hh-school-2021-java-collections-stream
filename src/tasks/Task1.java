package tasks;

import common.Person;
import common.PersonService;
import common.Task;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
Задача 1
Метод на входе принимает List<Integer> id людей, ходит за ними в сервис
(он выдает несортированный Set<Person>, внутренняя работа сервиса неизвестна)
нужно их отсортировать в том же порядке, что и переданные id.
Оценить асимпотику работы
 */
public class Task1 implements Task {

  // !!! Редактируйте этот метод !!!
  /* Асимптотика O(n):
      -- за O(n) проходим List personIds для построения мапы {id : Person}
      -- снова за O(n) проходим List personIds и за O(1) по хэшу получаем персону из мапы
   */
  private List<Person> findOrderedPersons(List<Integer> personIds) {
    Set<Person> persons = PersonService.findPersons(personIds);

    Map<Integer, Person> idPersonMap = persons.stream()
            .collect(Collectors.toMap(
                    person -> person.getId(),
                    Function.identity()
            ));

    return personIds.stream()
            .map(id -> idPersonMap.get(id))
            .toList();
  }

  @Override
  public boolean check() {
    List<Integer> ids = List.of(1, 2, 3);

    return findOrderedPersons(ids).stream()
        .map(Person::getId)
        .collect(Collectors.toList())
        .equals(ids);
  }

}
