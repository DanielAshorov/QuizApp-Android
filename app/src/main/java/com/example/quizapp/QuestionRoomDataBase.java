package com.example.quizapp;

import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import android.util.Log;

@Database(entities = {Questions.class},version = 8)
abstract class QuestionRoomDatabase extends RoomDatabase {

    private static QuestionRoomDatabase INSTANCE;
    public abstract QuestionDao questionDao();

    public static synchronized QuestionRoomDatabase getInstance(final Context context) {

        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    QuestionRoomDatabase.class, "questions_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(RoomDBCallback)
                    .build();

            // or query a dummy select statement
            INSTANCE.query("select 1", null);
        }

        return INSTANCE;

    }

    private static RoomDatabase.Callback RoomDBCallback = new RoomDatabase.Callback() {


        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

        private QuestionDao questionDao;


        private PopulateDbAsyncTask(QuestionRoomDatabase db) {

            questionDao = db.questionDao();

        }

        @Override
        protected Void doInBackground(Void... voids) {

            Log.d("TRIVIA", "doInBackground: start insert");
            questionDao.insert(new Questions("Arsenal have won the only championship without a league loss", "True", "False", "Two Teams Hold This Record", "Everton Holds The Record", 1,"Sport", "en"));
            questionDao.insert(new Questions("Chelsea holds the lowest absorption record in the Premier League?","True", "False", "No,Man.United did","No,Arsenal did", 1,"Sport", "en"));
            questionDao.insert(new Questions("The Olympics are held every how many years?","5", "2", "4","8", 3,"Sport", "en"));
            questionDao.insert(new Questions("Real Madrid record holders of Champions League wins","True", "False", "Milan Holds The Record","Man.United Holds The Record", 1,"Sport", "en"));
            questionDao.insert(new Questions("Yossi Benayoun scored a goal against Real Madrid","Scored vs Barcelona", "False", "He didnt scord in the CL","True",  4,"Sport", "en"));
            questionDao.insert(new Questions("Cristiano Ronaldo plays for","Portugal", "Spain", "Argentina","Israel", 1,"Sport", "en"));
            questionDao.insert(new Questions("The record holder of titles in tennis is Novak Djokovic","True", "No, Andy Murray holds the record", "No, Federrer holds the record","False", 4,"Sport", "en"));
            questionDao.insert(new Questions("Which of the following won the Champions League","Neymar", "Paul Pogba", "Antoine Griezmann","Eden Hazard ", 1,"Sport", "en"));
            questionDao.insert(new Questions("Which of the following clubs is not from London","Tottenham", "Chelsea", "Everton","Arsenal", 3,"Sport", "en"));
            questionDao.insert(new Questions("Andy Murray won Wimbledon in 2016","No. Won in 2013", "True", "No. Won in 2015.","No. Won in 2014.", 2,"Sport", "en"));
            questionDao.insert(new Questions("Maccabi Tel Aviv basketball team has won the European Cup 5 times", "True", "Won 6 Times", "Won 2 Times", "Won 7 Times", 2,"Sport", "en"));
            questionDao.insert(new Questions("Omri Caspi is the first Israeli to play in NBA","True", "Guy Pnini ", "No one of the Israeli players played in the NBA","Danni Avadya", 1,"Sport", "en"));
            questionDao.insert(new Questions("The highest point difference in a basketball game in NBA is 73 points","True", "72 points", "54 Points","88 Points", 1,"Sport", "en"));
            questionDao.insert(new Questions("The record for most threes in NBA belongs to Klay Thompson","True", "Omri Caspi", "Carim Abdul Jabbar","Lebron James", 1,"Sport", "en"));
            questionDao.insert(new Questions("Chelsea have won the Champions League 4 times","True", "3", "1","2",  4,"Sport", "en"));


            questionDao.insert(new Questions("ארסנל זכו באליפות היחידה ללא הפסד ליגה", "לא נכון", "נכון", "שתי קבוצות מחזיקות בשיא יחד", "אברטון מחזיקה בשיא", 2,"ספורט", "he"));
            questionDao.insert(new Questions("צ'לסי מחזיקה בשיא הספיגות הנמוך ביותר בפריימרליג","נכון", "לא נכון", "מנצ'סטר יונייטד מחזיקה בשיא","ארסנל מחזיקה בשיא", 1,"ספורט", "he"));
            questionDao.insert(new Questions("בכל כמה שנים מתקיימת האולימפיאדה?","5", "2", "4","8", 3,"ספורט", "he"));
            questionDao.insert(new Questions("ריאל מדריד שיאנית זכיות ליגת האלופות","לא נכון", "נכון", "מנצסטר יונייטד מחזיקה בשיא","מילאן מחזיקה בשיא", 2,"ספורט", "he"));
            questionDao.insert(new Questions("יוסי בניון כבש שער נגד ריאל מדריד","כבש נגד ברצלונה", "לא נכון", "לא כבש בליגת האלופות","נכון",  4,"ספורט", "he"));
            questionDao.insert(new Questions("כריסטיאנו רונאלדו משחק עבור","פורטוגל", "ספרד", "ארגנטינה","ישראל", 1,"ספורט", "he"));
            questionDao.insert(new Questions("ג'וקוביץ' מחזיק בכמות התארים הגדולה ביותר של טניסאי בהיסטוריה","נכון", "אנדי מארי מחזיק בשיא זה", "פדרר מחזיק בשיא זה","לא נכון", 4,"ספורט", "he"));
            questionDao.insert(new Questions("מי מהבאים זכה בליגת האלופות","ניימאר", "פול פוגבה", "אנטואן גריזמן","עדן הזארד ", 1,"ספורט", "he"));
            questionDao.insert(new Questions("מי מהמועדנים הבאים אינו מלונדון","טוטנהאם", "צ'לסי", "אברטון","ארסנל", 3,"ספורט", "he"));
            questionDao.insert(new Questions("אנדי מארי זכה בווימבלדון בשנת 2016","זכה ב2013", "נכון", "זכה ב2015","זכב ב2014", 2,"ספורט", "he"));
            questionDao.insert(new Questions("מכבי תל אביב בכדורסל זכו 5 פעמים בגביע אירופה", "נכון", "זכו פעמיים", "זכו 6 פעמים", "זכו 7 פעמים", 3,"ספורט", "he"));
            questionDao.insert(new Questions("עמרי כספי הוא הכדורסלן הישראלי הראשון ב נ.ב.א","נכון", "גיא פניני הוא הראשון", "דני אבדיה","מעולם לא שיחק ישראלי ב NBA", 1,"ספורט", "he"));
            questionDao.insert(new Questions("הפרש הנקודות הכי גדול איי פעם במשחק כדורגל ב נ.ב.א עומד על 73 נקודות","88 נקודות", "נכון", "72 נקודות","54 נקודות", 2,"ספורט", "he"));
            questionDao.insert(new Questions("קלי טומפסון מחזיק בשיא שלשות בהיסטוריה ב נ.ב.א","נכון", "עמרי כספי", "קארים עבדול ג'אבר","לברון ג'יימס", 1,"ספורט", "he"));
            questionDao.insert(new Questions("צ'לסי זכו בליגת האלופות 4 פעמים","3", "נכון", "1","2",  4,"ספורט", "he"));


            questionDao.insert(new Questions("In which year did Justin Bieber release Baby","2012", "2009", "2006","2007", 2,"Music", "en"));
            questionDao.insert(new Questions("Who sang in James Bond movie (Skyfall)","Adele", "Rihanna", "Tina Turner","Madonna", 1,"Music", "en"));
            questionDao.insert(new Questions("What is the real name of Elton john","Paul David Hewson", "Robyn Fenty", "Stevland Hardaway Judkins","Reginald Dwight", 4,"Music", "en"));
            questionDao.insert(new Questions("Israel won the Eurovision Song Contest for the first time in ___","none", "In 1966", "In 1959","In 1988", 1,"Music", "en"));
            questionDao.insert(new Questions("Lior Narkis represented Israel at the Eurovision Song Contest?","No, Yehuda Levy represent", "True", "No, Haim Ifergan represent","No, Reuven the angel represent", 2,"Music", "en"));
            questionDao.insert(new Questions("What is the real name of Reuven the Angel?","Reuven levy is the real name", "Reuven Alfasi is the real name", "Reuven Shimoni is the real name","Reuven Cohen is the real name", 3,"Music", "en"));
            questionDao.insert(new Questions("Eyal Golan's first song was released in 1973","In 1999", "True", "In 1990","In 1993", 4,"Music", "en"));
            questionDao.insert(new Questions("Arik Einstein's guitar and violin song written by Shalom Hanoch","Written by Arik Einstien", "True", "Written by Shem-tov levy","Written by Yehunathan geffen", 2,"Music", "en"));
            questionDao.insert(new Questions("Yitzhak Klepter was in the Kaveret band ","Was in Machina band", "True", "Was in High Five band","Was in High windows band", 2,"Music", "en"));
            questionDao.insert(new Questions("Maty Caspi composed the music of a celebration in snooker","True", "Lior Narkis", "Moshe Peretz","Sholomo Artzi", 1,"Music", "en"));
            questionDao.insert(new Questions("Aviv Geffen's first album was \"It's just the moonlight\"","True", "You and Me", "Lost Years","Beni Was here", 1,"Music", "en"));
            questionDao.insert(new Questions("Linkin Park Band was founded in ___","1989", "1996", "1992","1990", 2,"Music", "en"));
            questionDao.insert(new Questions("The song \"Gang Ham Style\" published by a South Korean singer","North Korean", "Island", "French","True", 1,"Music", "en"));
            questionDao.insert(new Questions("Noa Kirel lives in Petah Tikva","True", "Tel Aviv", "Kfar Sava","Raanana", 4,"Music", "en"));
            questionDao.insert(new Questions("Taylor Swift launched ___ albums in 2020","5", "3", "2","1", 3,"Music", "en"));


            questionDao.insert(new Questions("מה הוא שמו האמיתי של אלטון ג'ון","פ'ול דיוויד", "רובין פנטי", "סטיב הארדווי ג'נקינס","רדינאלד דוויט", 4,"מוזיקה", "he"));
            questionDao.insert(new Questions("ישראל זכתה בארוויזיון בפעם הראשונה בשנת ___","אף תשובה לא נכונה", "1966", "1959","1988", 1,"מוזיקה", "he"));
            questionDao.insert(new Questions("ליאור נרקיס ייצג את ישראל בארוויזיון?","לא, חיים איפרגן ייצג", "נכון", "לא, יהודה לוי ייצג","לא, ראובן המלאך ייצג", 2,"מוזיקה", "he"));
            questionDao.insert(new Questions("באיזו שנה יצאו לאור שירו של ג'סטין ביבר בייבי?","2012", "2009", "2006","2007", 2,"מוזיקה", "he"));
            questionDao.insert(new Questions("מה השם האמיתי של ראובן המלאך?","ראובן לוי", "ראובן אלפסי", "ראובן שמעוני","ראובן כהן", 3,"מוזיקה", "he"));
            questionDao.insert(new Questions("השיר הראשון של אייל גולן יצא בשנת 1973","יצא בשנת 1973", "יצא בשנת 1999", "נכון","יצא בשנת 1993", 4,"מוזיקה", "he"));
            questionDao.insert(new Questions("השיר גיטרה וכינור של אריק איינשטיין נכתב על ידי שלום חנוך","אריק איינשטיין כתב", "נכון", "שם טוב לוי כתב","יהונתן גפן כתב", 2,"מוזיקה", "he"));
            questionDao.insert(new Questions("יצחק קלפטר היה בלהקת כוורת","בלהקת משינה", "נכון", "בלהקת היי פייב","בלהקת החלונות הגבוהים", 2,"מוזיקה", "he"));
            questionDao.insert(new Questions("מתי כספי הלחין את המוזיקה של חגיגה בסנוקר","נכון", "ליאור נרקיס", "משה פרץ","שלמה ארצי", 1,"מוזיקה", "he"));
            questionDao.insert(new Questions("מי שר את שיר הפתיחה בסרט סקייפול של ג'יימס בונד","אדל", "ריהאנה", "טינה טרנר","מדונה", 1,"מוזיקה", "he"));
            questionDao.insert(new Questions("האלבום הראשון של אביב גפן היה \"זה רק אור הירח\" ","נכון", "את ואני", "שנים אבודות","בני היה פה", 1,"מוזיקה", "he"));
            questionDao.insert(new Questions("הלהקה לינקין פארק נוסדה בשנת 1996","1989", "נכון", "1992","1990", 2,"מוזיקה", "he"));
            questionDao.insert(new Questions("השיר \"גאנג האם סטייל\" שוחרר על ידי זמר דרום קוריאני","נכון", "צפון קוריאני", "איסלנדי","צרפתי", 1,"מוזיקה", "he"));
            questionDao.insert(new Questions("נועה קירל גרה בפתח תקווה","בנתניה", "נכון", "בתל אביב","ברעננה", 4,"מוזיקה", "he"));
            questionDao.insert(new Questions("טיילור סוויפט השיקה שני אלבומים בשנת 2020","5", "3", "נכון","1", 3,"מוזיקה", "he"));


            questionDao.insert(new Questions("The spider has ___ legs","8", "5", "6","7", 1,"Animals", "en"));
            questionDao.insert(new Questions("The tallest animal in the world is","Giraffe", "Mouse", "Cat","Fish", 1,"Animals", "en"));
            questionDao.insert(new Questions("The animal that barks is ___","Cat", "Dog", "Ant","Monkey", 2,"Animals", "en"));
            questionDao.insert(new Questions("The mammal that can fly is ___","Horse", "Bat", "Cat","Dog", 2,"Animals", "en"));
            questionDao.insert(new Questions("The fastest animal in the world is ___","Cheetah", "Dog", "Bat","Spider", 1,"Animals", "en"));
            questionDao.insert(new Questions("What is the type of the animal Kangaroos","Home Cat", "Trees Monkey", "Marsupial","Wild Bear", 3,"Animals", "en"));
            questionDao.insert(new Questions("An elephant's pregnancy takes about ___  years","1", "4", "3","2", 4,"Animals", "en"));
            questionDao.insert(new Questions("The insect that can carry 50 times its body weight is an ___","Bee", "Ant", "Bird","Chicken", 2,"Animals", "en"));
            questionDao.insert(new Questions("The octopus has ___ hearts","2", "1", "4","3", 4,"Animals", "en"));
            questionDao.insert(new Questions("Lobster has ___ legs","2", "12", "10","3", 3,"Animals", "en"));
            questionDao.insert(new Questions("The snake sticks its tongue out to ___","eat", "smell", "kill","see", 2,"Animals", "en"));
            questionDao.insert(new Questions("___ is a kind of monkey","Spider", "Tiger", "Baboon","Mouse", 3,"Animals", "en"));
            questionDao.insert(new Questions("The shark has ___ bones","100", "0", "3","84", 2,"Animals", "en"));
            questionDao.insert(new Questions("Kukuriku is the ___ voice","Rooster", "Fox", "Tiger","Elephant", 1,"Animals", "en"));
            questionDao.insert(new Questions("The color of the giraffe's tongue is ___","Red", "Yellow", "Blue","Purple", 4,"Animals", "en"));
            questionDao.insert(new Questions("It is customary to say that a cat has ___ souls","8", "1", "4","9", 4,"Animals", "en"));


            questionDao.insert(new Questions("איזו סוג חיה היא קנגרו?","בייתית", "עצית", "כיס","בר", 3,"חיות", "he"));
            questionDao.insert(new Questions("לעכביש יש ___ רגליים","8", "5", "7","6", 1,"חיות", "he"));
            questionDao.insert(new Questions("החיה הגבוהה בעולם היא ___","ג'ירפה", "עכבר", "חתול","דג", 1,"חיות", "he"));
            questionDao.insert(new Questions("החיה שנובחת היא ___","חתול", "כלב", "נמלה","קוף", 2,"חיות", "he"));
            questionDao.insert(new Questions("היונק שמסוגל לעוף הוא ___","סוס", "עטלף", "חתול","כלב", 2,"חיות", "he"));
            questionDao.insert(new Questions("החיה המהירה בעולם היא ___","צ'יטה", "כלב", "עטלף","עכביש", 1,"חיות", "he"));
            questionDao.insert(new Questions("הריון של פיל נמשך כ___ שנים","1", "2", "3","4", 2,"חיות", "he"));
            questionDao.insert(new Questions("לתמנון יש ___ לבבות","1", "4", "3","2", 3,"חיות", "he"));
            questionDao.insert(new Questions("ללובסטר יש ___ רגליים","2", "12", "10","3", 3,"חיות", "he"));
            questionDao.insert(new Questions("הנחש מוציא את לשונו כדי ___","לאכול", "להריח", "להרוג","לראות", 2,"חיות", "he"));
            questionDao.insert(new Questions("___ הוא סוג של קוף","נמר", "בבון", "עכבר","עכביש", 2,"חיות", "he"));
            questionDao.insert(new Questions("לכריש יש ___ עצמות","100", "0", "3","84", 2,"חיות", "he"));
            questionDao.insert(new Questions("קוקוריקו הוא הצליל שמשמיע ___","תרנגול", "שועל", "נמר","פיל", 1,"חיות", "he"));
            questionDao.insert(new Questions("צבע הלשון של ג'ירפה הוא ___","אדום", "צהוב", "כחול","סגול", 4,"חיות", "he"));
            questionDao.insert(new Questions("נהוג לומר שיש לחתול ___ נשמות","8", "1", "4","9", 4,"חיות", "he"));


            questionDao.insert(new Questions("Harry Potter and the Philosopher's Stone is the ___ in the series","Second", "Third", "First","Fourth", 3,"Movies", "en"));
            questionDao.insert(new Questions("Yosef Shiloah played ___in the movie Snooker Celebration","Charlie", "Sasson", "Miko","Salvador", 4,"Movies", "en"));
            questionDao.insert(new Questions("In the movie The Matrix Nao takes the ___ ball","Blue", "Yellow", "Green","Red", 4,"Movies", "en"));
            questionDao.insert(new Questions("The film Pulp Fiction was released in ___","1990", "1994", "1985","1978", 2,"Movies", "en"));
            questionDao.insert(new Questions("Joker is a character in the world of ___","Batman", "Spiderman", "Disney","Borekas", 1,"Movies", "en"));
            questionDao.insert(new Questions("The famous sentence of Robert De Niro ___ is taken from the movie \"Taxi Driver\"","Are you talking to me?", "Who call me?", "Those who believe are not afraid","Every Saturday there is a Saturday night", 1,"Movies", "en"));
            questionDao.insert(new Questions("In the movie Charlie and a half, Miko said ___","Who's dealing with?", "What does the worm want?", "he's badly injured and we don't know what he has","Wallac who will marry you ya meatball", 3,"Movies", "en"));
            questionDao.insert(new Questions("___ wrote the script for the \"Rocky\" films","Trantino", "Arnold Schwarzenegger", "Silvester Stallon","Zeev Revah", 3,"Movies", "en"));
            questionDao.insert(new Questions("The highest-grossing film in history is ___","The Avangers", "Titanic", "Free-Shuli","Avatar", 4,"Movies", "en"));
            questionDao.insert(new Questions("The real name of actress Lola in the movie \"Alex is sick of love\" is ___","Gila almagor", "Sara Hacohen", "Lola lil","Hana Laslao", 2,"Movies", "en"));
            questionDao.insert(new Questions("The first dark-skinned woman to win an Oscar for Best Actress is ___","Halle Berry", "Jennifer Aniston", "Courney Cox","Scarllett Johansson", 1,"Movies", "en"));
            questionDao.insert(new Questions("A third ___ created the Israeli film \"Free Shuli\"","Pale Tracker", "Prozak", "Ma Kashur","Mango band", 3,"Movies", "en"));
            questionDao.insert(new Questions("In the movie Charlie and half Charlie's mother is called ___","Flora", "Shoshana", "Salvadora","Adi", 1,"Movies", "en"));
            questionDao.insert(new Questions("In the Harry Potter movie the name of the wizarding school is ___","Dambeldor", "Fishenzon", "Hogwarts","Darko", 3,"Movies", "en"));
            questionDao.insert(new Questions("On the TV show in SpongeBob Patrick is a ___ type creature","Octopus", "Elephant", "Squirrel","Starfish", 4,"Movies", "en"));
            questionDao.insert(new Questions("The Lion King was released in ___","1993", "1994", "1992","1990", 2,"Movies", "en"));
            questionDao.insert(new Questions("The movie Fight Club was released in ___","1999", "1988", "1990","1993", 1,"Movies", "en"));


            questionDao.insert(new Questions("הארי פוטר ואבן החכמים הוא הסרט ה___ בסדרה","שני", "שלישי", "ראשון","רביעי", 3,"סרטים", "he"));
            questionDao.insert(new Questions("יוסף שילוח שיחק את ___ בסרט חגיגה בסנוקר","צ'ארלי", "ששון", "מיקו","סלבדור", 4,"סרטים", "he"));
            questionDao.insert(new Questions("בסרט מטריקס ניאו לוקח את הכדור ___","כחול", "צהוב", "ירוק","אדום", 4,"סרטים", "he"));
            questionDao.insert(new Questions("הסרט ספרות זולה יצא לאור בשנת ___","1990", "1994", "1985","1978", 2,"סרטים", "he"));
            questionDao.insert(new Questions("ג'וקר היא דמות בעולם של ___","באטמן", "ספיידרמן", "דיסני","סרטי בורקס", 1,"סרטים", "he"));
            questionDao.insert(new Questions("המשפט המפורסם של רוברט דה נירו ___ שלקוח מהסרט נהג מונית","אתה מדבר אליי?", "מי קרא לי?", "מי שמאמין לא מפחד","לכל שבת יש מוצאי שבת", 1,"סרטים", "he"));
            questionDao.insert(new Questions("בסרט צ'רלי וחצי אמר מיקו ___","מה רוצה התולעת?", "מי מתעסק?", "הוא פצוע קשה לא יודעים מה יש לו","וואלק מי תתחתן איתך יא קציצה", 3,"סרטים", "he"));
            questionDao.insert(new Questions("___ כתב את התסריט של סרטי רוקי","ארנולד שווארצאנגר", "קוונטין טרנטינו", "סילבסטר סטאלון","זאב רווח", 3,"סרטים", "he"));
            questionDao.insert(new Questions("הסרט המכניס ביותר בהיסטוריה הוא ___","הנוקמים", "טיטאניק", "לשחרר את שולי","אוואטר", 4,"סרטים", "he"));
            questionDao.insert(new Questions("שמה האמיתי של השחקנית לולה בסרט \"אלכס חולה אהבה\" הוא ___","גילה אלמגור", "שרה הכהן", "לולה ליל","חנה לסלאו", 2,"סרטים", "he"));
            questionDao.insert(new Questions("האישה כהת העור הראשונה שזכתה באוסקר בפרס השחקנית הטובה ביותר היא ___","האלי בארי", "ג'ניפר אניסטון", "קורטני קוקס","סקרלט ג'והנסון", 1,"סרטים", "he"));
            questionDao.insert(new Questions("שלישית ___ יצרה את הסרט הישראלי לשחרר את שולי","הגשש החיוור", "פרוזק", "מה קשור","מנגו", 3,"סרטים", "he"));
            questionDao.insert(new Questions("בסרט צ'רלי וחצי אימו של צ'רלי נקראת ___","פלורה", "שושנה", "סלבדורה","עדי", 1,"סרטים", "he"));
            questionDao.insert(new Questions("בסרט הארי פוטר שמו של בית הספר לקוסמים הוא ___","דמבלדור", "פישנזון", "הוגוורטס","דארקו", 3,"סרטים", "he"));
            questionDao.insert(new Questions("בתוכנית הטלוויזיה בבוספוג פטריק הוא יצור מסוג ___","תמנון", "פיל", "סנאי","כוכב ים", 4,"סרטים", "he"));
            questionDao.insert(new Questions("הסרט מלך האריות יצא לאור בשנת ___","1993", "1994", "1992","1990", 2,"סרטים", "he"));
            questionDao.insert(new Questions("הסרט מועדון קרב יצא לאור בשנת ___","1999", "1998", "1990","1993", 1,"סרטים", "he"));


            questionDao.insert(new Questions("The national dish in Israel is Falafel","True", "Pasta", "Pizza","Hamburger", 1,"Food", "en"));
            questionDao.insert(new Questions("The national dish in Italy is ___","Hamburger", "Pasta", "Hummus","Mayo", 2,"Food", "en"));
            questionDao.insert(new Questions("Pasta bolognese is a mixture of pasta with ___","Cheese", "Fishes", "Meat","Sea Food", 3,"Food", "en"));
            questionDao.insert(new Questions("Quba okra belongs to the Iraqi community","Marrocco", "England", "French","True", 4,"Food", "en"));
            questionDao.insert(new Questions("The origin of the khachapuri is in ___","Australian", "Vienna", "Georgia","Island", 3,"Food", "en"));
            questionDao.insert(new Questions("One of the ingredients in hummus is Thina","Parsley", "True", "Mushroom","Garlic", 2,"Food", "en"));
            questionDao.insert(new Questions("Tabasco was invented in ___","Japan", "China", "True","Spain", 4,"Food", "en"));
            questionDao.insert(new Questions("The most eaten food in the world is rice","True", "Chick", "Salad","Chicken", 1,"Food", "en"));
            questionDao.insert(new Questions("Chicken is part of its origin in the ___","Cow", "Goat", "Chicken is vegetarian","Chicken", 4,"Food", "en"));
            questionDao.insert(new Questions("Tea is the most consumed drink in the world","Water is the drink", "Cola is the drink", "True","Beer is the drink", 3,"Food", "en"));
            questionDao.insert(new Questions("Meat aging is a controlled decomposition process of the meat","True", "Process of lubrication", "Process of freezing","Process of cutting", 1,"Food", "en"));
            questionDao.insert(new Questions("Tea with milk originated in ___","England", "Israel", "Finland","Spain", 1,"Food", "en"));
            questionDao.insert(new Questions("McDonald's is the chain that sells the Big Mac dish","Burgeranch", "Burger King", "True","Hadson", 3,"Food", "en"));
            questionDao.insert(new Questions("The main ingredient in Falafel is ___","Thina", "Hummus", "Vegetable","Oil", 2,"Food", "en"));
            questionDao.insert(new Questions("The most eaten food in the world is ___","Rice", "Onion", "Meat","Pasta", 4,"Food", "en"));

            questionDao.insert(new Questions("המאכל הלאומי בישראל הוא ___","פלאפל", "פסטה", "המבורגר","פיצה", 1,"אוכל", "he"));
            questionDao.insert(new Questions("המאכל הלאומי באיטליה הוא ___","המבורגר", "פסטה", "חומוס","מיונז", 2,"אוכל", "he"));
            questionDao.insert(new Questions("פסטה בולונז היא תערבות של פסטה עם ___","גבינות", "דגים", "בשר","פירות ים", 3,"אוכל", "he"));
            questionDao.insert(new Questions("קובה במיה שייך לעדה העיראקית","מרוקאית", "אנגלית", "צרפתית","נכון", 4,"אוכל", "he"));
            questionDao.insert(new Questions("מקור החצ'פורי הוא ב___","גאורגיה", "אוסטרליה", "וינה","איסלנד", 1,"אוכל", "he"));
            questionDao.insert(new Questions("המרכיב העיקרי בחומוס הוא טחינה","פטרוזיליה", "נכון", "פטריות","שום", 2,"אוכל", "he"));
            questionDao.insert(new Questions("הטבסקו הומצא במקסיקו","יפן", "סין", "נכון","ספרד", 4,"אוכל", "he"));
            questionDao.insert(new Questions("המאכל הנאכל ביותר בעולם הוא ___","אורז", "פרגיות", "סלט","עוף", 1,"אוכל", "he"));
            questionDao.insert(new Questions("תה הוא המשקה הנצרך ביותר בעולם","מים", "קולה", "נכון","בירה", 3,"אוכל", "he"));
            questionDao.insert(new Questions("עישון בשר הוא הרקבה הכנה מבוקר של הבשר","נכון", "תהליך של שימון", "תהליך של הקפאה","תהליך של חיתוך", 1,"אוכל", "he"));
            questionDao.insert(new Questions("תה עם חלב מקורו ב___","פינלנד", "אנגליה", "ישראל","ספרד", 2,"אוכל", "he"));
            questionDao.insert(new Questions("מקדונלדס היא הרשת שמוכרת את מנת הביג מק","בורגראנץ", "נכון", "בורגר קינג","האדסון", 2,"אוכל", "he"));
            questionDao.insert(new Questions("המרכיב העיקרי בפלאפל הוא ___","טחינה", "חומוס", "ירק","שמן", 2,"אוכל", "he"));
            questionDao.insert(new Questions("המזון הנצרך ביותר בעולם הוא ___","אורז", "בצל", "בשר","פסטה", 4,"אוכל", "he"));
            questionDao.insert(new Questions("פרגית היא חלק שמקורו ב___","פרה", "עז", "פרגית זה טבעוני","תרנגול", 4,"אוכל", "he"));


            Log.d("TRIVIA", "doInBackground: end insert");


            return null;
        }
    }
}



