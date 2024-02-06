# java-sprint2-hw
Second sprint homework

## ТЗ "Автоматизация бухгалтерии".
---
## Постановка задачи
---
Отдел бухгалтерии парка развлечений пользуется простыми excel-таблицами для ведения бюджета. В их работе постоянно возникают ошибки при вводе данных и подсчёте балансов. Руководство компании поставило перед вами сложную задачу — создать новое приложение для бухгалтерии.

Оно должно предоставлять следующий функционал:
* Иметь консольный интерфейс для управления программой.
* Считывать месячные и годовые отчёты бухгалтерии из файлов и приводить их в объекты приложения.
* Проводить сверку данных по месячным и годовым отчётам.
* Выводить информацию о месячных и годовом отчёте.


## Формат входных файлов
---
Бухгалтерия готова предоставить данные о своей деятельности в виде файлов в формате CSV. Нужно разбить входящий файл на составляющие и преобразовать к объектам приложения. Приложение должно работать с двумя видами отчётов:
* Месячный отчёт, содержащий данные о доходах и расходах в рамках одного календарного месяца. В программе они представляются классом MonthlyReport.
* Годовой отчёт, содержащий ровно по 2 записи на каждый из 12 месяцев — общий доход и расход за этот месяц. Представлен классом YearlyReport.

### Именование файлов

Файлы с отчётами именуются определённым образом, чтобы упростить их считывание и обработку.


#### Именование месячных отчётов

Имена файлов с месячными отчётами имеют формат m.YYYYMM.csv, где:

* m — буква m в начале файла, чтобы отделить отчёты за месяц и отчёты за год;
* YYYY — год. Например, 2021;
* MM — месяц строго двумя цифрами. Счёт начинается с единицы, то есть 01 — «январь», а 11 — «ноябрь».

Пример именований: m.202001.csv — месячный отчёт за январь 2020 года


### Именование годовых отчётов

Имена файлов с годовым отчётом имеют формат y.YYYY.csv, где:

* y — буква y в начале файла, чтобы отделить отчёты за месяц и отчёты за год;
* YYYY — год. Например, 2021.

Пример именований: y.2020.csv — годовой отчёт за 2020 год


### Формат месячного отчёта

Месячный отчёт содержит информацию о всех тратах, произведённых в течение календарного месяца. Сюда попадает информация как о доходах, так и о расходах парка развлечений. Пример CSV файла с месячным отчётом:

item_name,is_expense,quantity,sum_of_one
Воздушные шарики,TRUE,5000,5
Автоматы с мороженным,TRUE,12,15000
Продажа мороженного,FALSE,1000,120

* item_name — название товара;
* is_expense — одно из двух значений: TRUE или FALSE. Обозначает, является ли запись тратой (TRUE) или доходом (FALSE);
* quantity — количество закупленного или проданного товара;
* sum_of_one — стоимость одной единицы товара. Целое число.


### Формат годового отчёта

Годовой отчёт содержит информацию о всех тратах, произведённых в течение года. Он содержит по две записи на каждый месяц. Месяц обозначается строго двумя цифрами, начиная с единицы, то есть 01 — «январь», а 11 — «ноябрь». Пример CSV файла с годовым отчётом:

month,amount,is_expense
01,100000,false
01,30000,true
02,321690,false
02,130000,true
03,999999,true
03,999999,false

Строка годового отчёта состоит из трёх полей:

* month — месяц. Целое число;
* amount — сумма;
* is_expense — одно из двух значений: true или false. Обозначает, является ли запись тратой (true) или доходом (false).