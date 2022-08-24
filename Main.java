package stream_task2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
		List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
		Collection<Person> persons = new ArrayList<>();
		for (int i = 0; i < 10_000_000; i++) {
			persons.add(new Person(names.get(new Random().nextInt(names.size())),
					families.get(new Random().nextInt(families.size())), new Random().nextInt(100),
					Sex.values()[new Random().nextInt(Sex.values().length)],
					Education.values()[new Random().nextInt(Education.values().length)]));
		}
		long minor = persons.stream().filter(x -> x.getAge() < 18).count();
		System.out.println(minor);
		
		List<String> conscript = persons.stream().filter(x -> x.getSex() == Sex.MAN)
				.filter(x -> (x.getAge() >= 18 && x.getAge() <= 27)).map(x -> x.getFamily())
				.collect(Collectors.toList());
		
		List<Person> worker = persons.stream()
				.filter(x -> x.getEducation().equals(Education.HIGHER))
				.filter(x -> x.getAge() >= 18)
				.filter(x -> (x.getAge() <= 65 && x.getSex().equals(Sex.MAN))
						|| (x.getAge() <= 60 && x.getSex().equals(Sex.WOMAN)))
				.sorted((x, y) -> x.getFamily().compareTo(y.getFamily()))
				.collect(Collectors.toList());
	}

}
