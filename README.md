# GIT & BUILD TOOLS QUESTION

1. Что такое инструменты для сборки и для чего их используют ? - Инструменты сборки - это программы, которые автоматизируют создание исполняемых приложений из исходного кода
2. Жизненный цикл сборки Gradle. -Initialization,Konfiguration,Execution
3. Как запустить Gradle с консоли? - gradle run
4. Как запустить только тесты Gradle? - gradle test
5. Как сгенерировать javadoc? - gradle javadoc
6. Что такое Gradle-проект и что такое Gradle-таски? - Задачи представляют собой именованные наборы инструкций, которые Gradle запускает, выполняя сборку приложения
7. Как отобразить проекты Gradle? - gradle project
8. Как отобразить таски Gradle? - gradle appName:tasks
9. Транзитивные зависимости. - Транзитивная зависимость — это тот артефакт, от которого зависит прямая зависимость проекта.
10. Кэш зависимостей. - В Gradle реализована система кэширования, которая по умолчанию хранит зависимости в течение 24 часов, но это поведение можно переопределить.
11. Модульная зависимость. - Зависимость от другого модуля проекта 
12. Для чего применяется многопроектная сборка? - Для удобной параллельной разработки разных модулей проекта, не зависящих друг от друга,но имеющих доступ к общим ресурсам.
13. Что такое plugin? - Набор задач
14. Что такое Gradle Wrapper и для чего он нужен? - Для того что бы можно было скачать проект и сразу запустить его,не устанавливая дополнительно Gradle на компьютер
15. Как можно передать параметры через команды сборки? -  --args = "  "
16. Что такое VCS? - Система Контроля Версий
17. Какие есть системы контроля версий? -GIT,Subversion
18. Концепции GIT - branch,commit,merge,tree, ...
19. Что такое merge? - Слияние двух веток в одну с применением всех изменений 
20. Что лежит внутри .git папки? - В .git хранится все, что вы делаете в своем проекте, только в сжатом формате. Поэтому из этой директории можно восстановить репозиторий.

# REST API Basics

1. IoC & DI - DI реализация IoC, помимо DI еще factory method,service locator
2. Минимальные зависимости для поднятия контекста - spring context
3. Spring Bean Lifcycle - https://habr.com/ru/post/470305/
4. Виды конфигурации спринга - джава,аннотации,xml
5. Какая конфигурация лучше? - никакая,у всех свои +-
6. Какая разница между repository,component,service,controller ? - component родитель,остальные дети
7. Можно ли написать свой scope если да то как? - можно,имплементировать интерфейс scope и зарегистрировать свой класс в BeanFactoryPostProccess
8. REST,SOAP,Richardson model - https://habr.com/ru/post/131343/  https://www.intervolga.ru/blog/projects/relsy-veb-integratsii-rest-i-soap/  https://ru.qaz.wiki/wiki/Richardson_Maturity_Model https://m.habr.com/ru/post/319984/
9. SOLID принципы. https://www.youtube.com/watch?v=rtmFCcjEgEw&t=1992s
10. END2END tests. https://habr.com/ru/post/417395/
11. ACID. https://ru.wikipedia.org/wiki/ACID
12. Транзакции вокруг селектов.
13. Stream API.
