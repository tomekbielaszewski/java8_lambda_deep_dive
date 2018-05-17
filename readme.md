# Example 1.

	- pokaz jednowatkowy wynik
	- pokaz wynik z parallel
	- Lambdy nie mogą trzymac stanu na zewnatrz

# Example 2

## part 1

	- [!] zapytaj "Co zwrócą te dwie linijki?" this::metoda vs Class::metoda
	
	- ostatnie dwa przyklady wymagaja wlasnych interface'ow
		
		@FunctionalInterface
		interface TriConsumer<IN1, IN2, IN3> {
			void accept(IN1 attribute1, IN2 attribute2, IN3 attribute3);
		}

		@FunctionalInterface
		interface TriFunction<CLASS, OUT, IN1, IN2> {
			OUT apply(CLASS clazz, IN1 attribute1, IN2 attribute2);
		}

## part 2

	- zajrzyj co przyjmuje forEach(Consumer)
	
	- pokaz ze mozna przekazac Holder::getHolded
			holders.forEach(Holder::getHolded);
	
	- pokaz ze NIE mozna przekazac getHoldedFunction
			holders.forEach(getHoldedFunction);
			
	- pokaz ze Holder::getHolded mozna przypisac do Consumer
			Consumer<Holder> getHoldedConsumer = Holder::getHolded;
			
	- special void compatibility rule: 
	    Jesli lambda zwraca wartosc to mozna ją przypisac do deskryptora funkcji zwracającego voida (tutaj: consumer)
	    Tak samo jak mozna ignorowac zwracaną wartość z metod 
			
	- pokaz ze powyzsze mozna przekazac do forEach()
			holders.forEach(getHoldedConsumer);
			
	- pokaz jednolinijkową lambdę która odpowiada powyzszemu
			holders.forEach(h -> h.getHolded());
			
	- pokaz wielolinijkowa lambdę która odpowiada Holder::getHolded
			holders.forEach(h -> {
				h.getHolded();
				return;
			});
	
	- pokaz ze IntelliJ nie marudzi przy mapie
			holders.stream().map(Holder::getHolded);
			
# Example 3

## part 1

	- pokaz skompilowane pliki kiedy masz kod z klasą anonimową
	
	- pokaz kod dekompilowany i pokaz inicjalizacje anonimowego obiektu
			javap -p -c target\...\Example3_1

	- pokaz skompilowane pliki kiedy masz kod z lambdą
	
	- pokaz kod dekompilowany i pokaz wywolanie invokedynamic
			javap -p -c target\...\Example3_1
	
## part 2

	- wypisz na ekranie wszystkie metody klasy
			Arrays.asList(Example3_2_LambdaGeneratedCode.class.getDeclaredMethods())
                .forEach(System.out::println);
				
	- [!] zapytaj co sie stanie kiedy nadpisze wygenerowana metode
			private static String lambda$run$0() {
				return "another sweet string";
			}
			
	- zdekompiluj kod - czemu nie ma wygenerowanej lambdy z forEach'a po metodach?
	
	- omów invokedynamic
