# Carcan Calin 324CD - POO TV

Pentru a realiza o implementare cat mai usor de modificat am inceput prin a gandi
clasele si modul in care vor interactiona intre ele. Am inceput prin a defini clase de input
a caror treaba a fost sa poata colecta date dintr-un fisier json. Acele date au fost adaugate
apoi intr-o clasa Database care va stoca toata informatia utila cum ar fi utilizatorii,
filmele, lista de filme curenta, utilizatorul curent si actiunile realizate. Acestea au fost
copiate folosind clase de tip factory e care le-am folosit la instantierea sau copierea de noi
obiecte de clasa User, Movie si inca unul pentru clasele ErrorMessage care vor fi folosite la
output.

Pentru a implementa mai apoi logica din spatele comenzilor de actiuni date la input,
am creat clasa CurrentPage care va tine minte pagina pe care ne aflam in orice moment
de timp si metodele care o fac sa treaca dintr-o pagina pe alta. Pentru implementarea
propriu-zisa a comenzilor am creat cate o clasa visitor pentru fiecare pagina. In functie
de pagina pe care ne aflam, CurrentPage apela vizitatorul specific care mai apoi executa
comenzile de tip change page si on page. Stiind care sunt actiunile permise pe
fiecare pagina am facut un switch pe baza tipului actiunii si apoi mai multe blocuri specifice
paginii pe care dorim sa ne mutam sau actiunii pe care dorim sa o executam.

Actiunile de change page depind strict de pagina pe care ne aflam, de exemplu cand ne aflam pe
pagina de home neautentificat, actiunile sunt executate de clasa VisitorHomeNAUTH unde
singurele actiuni permise sunt cele de change page pe paginile de login si register. 
Orice alt tip de actiune ar fi fost primit cu mesaj de eroare. Odata logat un utilizator,
avem acces la lista de filme curente, care nu sunt interzise in tara utilizatorului, 
si putem naviga in liniste pe celelalte pagini.

In cazul in care trebuie sa afisam un mesaj la output, care va fi creat
cu ajutorul clasei ErrorFactory, avem 2 variante, fie actiunea a fost
eronata si trebuie afisat un mesaj standard de eroare pe care l-am instantiat o singura data
folosind design pattern-ul singleton, sau actiunea a fost realizata cu succes caz in care afisam 
un mesaj specific pentru fiecare actiune continand datele despre utilizatorul curent si listele
sale de filme. De exemplu, in cazul mutarii pe pagina movies ar fi trebuit 
incarcata intreaga lista de filme disponibila utilizatorului si afisata impreauna cu datele utilizatorului.
In cazul in care am fi avut actiunea de tip filter, aceasta lista nefiltrata de mai sus urma sa fie
modificata si afisata cu filmele ramase care corespund conditiilor de filtrare.

Pentru a asigura corectitudinea mesajului de output, obiectele care erau puse in output au
trebuit copiate complet pentru a nu suferi schimbari pe baza actiunilor date dupa afisarea acestora.
Aceste deep copy-uri au fost realizate tot cu ajutorul clasei ErrorFactory.


 