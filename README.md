Pharmaceutical source of innovative medicines

The application can:

From the administrator's side (website):
- add drugs
- edit drugs
- remove drugs
- add manufacturers
- remove manufacturers

On the part of the user (telegram):
- to receive a list of names of all innovative drugs;
- to receive a list of names of all manufacturers;
- to receive complete information about a drug by name;
- to receive a list of names of all drugs for a certain period of time.



Information for (web) -> http://localhost:8082

Information for telegram bot (in search field) -> @PharmaceuticalSourceOfDrugsBot

----------------------------------------------------------------------------------------------------------

To start, you should do the following:

1)Create a base named = pharmaceutical_source_of_drugs

2)Change the data to connect to the database {spring.datasource.username=?(your username)
                                              spring.datasource.password=?(your password)}

3)Get access from Ngrok and change tha data in field -> telegram.bot.webHookPath=?(your webHookPath)

(For example: telegram.bot.webHookPath=https://afdec90f8b64.ngrok.io)

4)Then in the browser enter a request in the address bar where put your webHookPath =
https://api.telegram.org/bot1895334926:AAGqOrP8A0bKsCsMSX7ArWIsVjHMA5gg2Z8/setWebhook?url=?(your webHookPath)/bot

(For example: https://api.telegram.org/bot1895334926:AAGqOrP8A0bKsCsMSX7ArWIsVjHMA5gg2Z8/setWebhook?url=https://68a5e1b84ee6.ngrok.io/bot)

5)After starting you must log in with

username = admin

password = admin

❗
![photo_2022-02-02_12-28-02](https://user-images.githubusercontent.com/76947403/152138609-6d7f73c0-0592-4c56-884f-7fdf44a328ad.jpg)

Good luck!!!
