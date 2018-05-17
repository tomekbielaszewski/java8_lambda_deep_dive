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
	
# Example 4

## part 1
    
    - pokaz ze mozna wywolac metode nowa refleksją za pomocą MethodHandle
            MethodType type = MethodType.methodType(int.class, new Class<?>[] {String.class});
            MethodHandles.Lookup lookup = MethodHandles.lookup();
            MethodHandle countHandle = lookup.findStatic(Example4_1_MethodHandle_PolymorphicSignature.class, "count", type);
    
            int foo = (int) countHandle.invokeExact("foo");
            System.out.println(foo);
            
    - pokaz kod o sygneturze podobnej do MethodHandle i ze wyglada identycznie (tak samo przyjmuje tablice i trzeba castowac return)
            int bar = (int) nonPolymorphicInvoke("foo");
            System.out.println(bar);
            
            private Object nonPolymorphicInvoke(Object... args) {
                return args.length;
            }
            
    - pokaz ze invokeExact ma @PolimorphicSignature w deklaracji metody co powoduje inną kompilację
    
    - pokaz dekompilowany kod
            javap -p -c target\classes\pl\grizwold\java8_lambda_deep_dive\Example4_1_MethodHandle_PolymorphicSignature.class
            
            # Wywołanie MethodHandle.invokeExact jest jakby metoda miala argument String i return int
                Method java/lang/invoke/MethodHandle.invokeExact:(Ljava/lang/String;)I
                
            # Wywołanie nonPolymorphicInvoke() tworzy tablicę string i jawnie castuje Integer na int
                40: anewarray     #7                  // class java/lang/String
                45: ldc           #13                 // String foo
                48: invokespecial #15                 // Method nonPolymorphicInvoke:([Ljava/lang/String;)Ljava/lang/Object;
                51: checkcast     #16                 // class java/lang/Integer
                54: invokevirtual #17                 // Method java/lang/Integer.intValue:()I

## part 2
    
    - opakuj użyty MethodHandle w ConstantCallSite
            private static CallSite bootstrap() throws Throwable {
                MethodType type = MethodType.methodType(int.class, new Class<?>[] {String.class});
                MethodHandles.Lookup lookup = MethodHandles.lookup();
                MethodHandle countHandle = lookup.findStatic(Example4_2_MethodHandle_CallSite.class, "count", type);
                return new ConstantCallSite(countHandle);
            }
            
    - użyj CallSite aby wywołać count
            CallSite callSite = bootstrap();
            
            int foo = (int) callSite.getTarget().invokeExact("foo");
            
    - CallSite jest to wrapper na MethodHandle. CallSite jest uzywany przez instrukcje invokedynamic.
    
## part 3

    - czas złożyć lambdę z typu Supplier i metody zwracajacej nam Stringa 
    
    - stworz MethodType dla
        - sygnatury metody w Supplier
        - istniejacej, docelowej metody
        - typu interfejsu funkcyjnego - Supplier
    
        MethodType supplierSignature = MethodType.methodType(Object.class);
        MethodType actualMethodSignature = MethodType.methodType(String.class);
        MethodType functionalInterfaceUsed = MethodType.methodType(Supplier.class);
        
    - nazwij jaką metodę w interfejsie funkcyjnym wywołujesz
        String functionalInterfaceMethodName = "get";
        
    - stwórz MethodHandle do istniejącej metody będącą ciałem lambdy
        MethodHandle lambdaBodyHandle = caller.findStatic(Example4_3_LambdaMetafactory.class, "getString", actualMethodSignature);
        
    - stwórz metafactory, które stworzy lambdę
        CallSite metafactory = LambdaMetafactory.metafactory(
                caller,
                functionalInterfaceMethodName,
                functionalInterfaceUsed,
                supplierSignature,
                lambdaBodyHandle,
                supplierSignature);

        MethodHandle lambdaFactory = metafactory.getTarget();
        
    - wywołaj lambdę
        Supplier<String> supplierLambda = (Supplier<String>) lambdaFactory.invoke();
        
    - LambdaMetafactory potrafi wygenerowac implementacje interfejsu funkcyjnego jako callsite ktory moze pozniej zostac wywolany przez JVM za pomoca invokedynamic
            