# Репозиторій призначений для зберігання курсової роботи студента 1-го курсу КПІ імені Сікорського, ФІОТ, ІСТ
# Вариводи Кирила Сергійовича
## Посилання на матеріал для підключення бази данних [YouTube](https://www.youtube.com/watch?v=ta8jJj1PZdg)
> Додатково треба буде вставити залежність в IDE, якщо ви використовуєте maven
```xml
      <dependency>
          <groupId>org.postgresql</groupId>
          <artifactId>postgresql</artifactId>
          <version>42.7.3</version>
      </dependency>
```
# Файл, який використовувався у відео знаходиться в репозиторії під назвою [KR.tar](KR.tar) (Зберігати обов'язково у .tar, при переході у .rar може статися збій)
Текст, який потрібно буде вставити у pgAdmin, щоб створити користувача з правами адміністратора:
```SQL
INSERT INTO "loginData"("uName", "uPass", admin) VALUES ('test', 'test', true)
```
