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