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


            questionDao.insert(new Questions("באיזו שנה יצאו לאור שירו של ג'סטין ביבר בייבי?","2012", "2009", "2006","2007", 2,"מוזיקה", "he"));
            questionDao.insert(new Questions("מי שר את שיר הפתיחה בסרט סקייפול של ג'יימס בונד","אדל", "ריהאנה", "טינה טרנר","מדונה", 1,"מוזיקה", "he"));
            questionDao.insert(new Questions("מה הוא שמו האמיתי של אלטון ג'ון","פ'ול דיוויד", "רובין פנטי", "סטיב הארדווי ג'נקינס","רדינאלד דוויט", 4,"מוזיקה", "he"));


            questionDao.insert(new Questions("What planet is closest to the sun","Mercury", "Neptune", "Jupiter","Venus", 1,"Science", "en"));
            questionDao.insert(new Questions("What is the largest planet","Mercury", "Neptune", "Jupiter","Venus", 3,"Science", "en"));
            questionDao.insert(new Questions("How many continents are there in the world","7", "4", "5","9", 1,"Science", "en"));

            questionDao.insert(new Questions("מי הוא כוכב הלכת הקרוב ביותר לשמש","מרקורי", "נפטון", "יופיטר","ונוס", 1,"מדע", "he"));
            questionDao.insert(new Questions("מה הוא כוכב הלכת הגדול ביותר","מרקורי", "נפטון", "יופיטר","ונוס", 3,"מדע", "he"));
            questionDao.insert(new Questions("כמה יבשות יש בעולם","7", "4", "5","9", 1,"Science", "מדע"));


            questionDao.insert(new Questions("Who is 007","Superman", "James Bond", "Ben Afflek","Richard Harrison", 2,"Movies", "en"));
            questionDao.insert(new Questions("Who is part of the Avengers","Batman", "Doom", "Wolverine","Hulk", 4,"Movies", "en"));
            questionDao.insert(new Questions("Who directed \"Kill Bill\"","Tarantino", "Spielberg", "Scorsese Martin","Christopher Nolan", 1,"Movies", "en"));
            questionDao.insert(new Questions("Who directed \"Inception\"","Tarantino", "Spielberg", "Scorsese Martin","Christopher Nolan", 4,"Movies", "en"));
            questionDao.insert(new Questions("Who played \"The Gladiator\"","Gerard Butler", "Tom Cruz", "Russell Crowe","Brad Pitt", 2,"Movies", "en"));
            questionDao.insert(new Questions("25.\tWho played \"The Mask\"","Jim Carrey", "Sasha Baron Cohen", "Adam Sandler","Brad Pitt", 1,"Movies", "en"));


            questionDao.insert(new Questions("מי הוא 007","סופרמן", "ג'יימס בונד", "בן אפלק","ריצ'רד באריסון", 2,"סרטים", "he"));
            questionDao.insert(new Questions("מי הוא חלק מהנוקמים","באטמן", "דום", "וולברין","הענק הירוק", 4,"סרטים", "he"));
            questionDao.insert(new Questions("מי ביים את קיל ביל","טרנטינו", "שפילברג", "מרטין סקורסזה","כריסטופר נולאן", 1,"סרטים", "he"));
            questionDao.insert(new Questions("מי ביים את התחלה","טרנטינו", "שפילברג", "מרטין סקורסזה","כריסטופר נולאן", 4,"סרטים", "he"));
            questionDao.insert(new Questions("מי שיחק את הגלדיאטור","ג'רארד באטלר", "טום קרוז", "ראסל קרואו","בראד פיט", 2,"סרטים", "he"));
            questionDao.insert(new Questions("מי שיחק את המסכה","ג'ים קארי", "סשה ברון כהן", "אדם סנדלר","בראד פיט", 1,"סרטים", "he"));


            questionDao.insert(new Questions("Which popular company designed the first CPU","Apple", "Dell", "Intel","IBM", 3,"Technology", "en"));
            questionDao.insert(new Questions("Which of the following is not a web browser","Chrome", "Google-Drive", "Safari","Edge", 2,"Technology", "en"));
            questionDao.insert(new Questions("What company developed a computer with the codename \"Lisa\"","Apple", "Microsoft", "Intel","IBM", 1,"Technology", "en"));
            questionDao.insert(new Questions("What does \"IT\" stand for","Integrated Technology", "Individual Technology", "Information Technology","Implied Technology", 3,"Technology", "en"));
            questionDao.insert(new Questions("Which of the following is not a software language","PHP", "C++", "C#","Docker", 4,"Technology", "en"));
            questionDao.insert(new Questions(" When was the internet created","1968", "1972", "1989","1992", 1,"Technology", "en"));
            questionDao.insert(new Questions("How many bits make a byte","16 bits", "8 bits", "24 bits","12 bits", 2,"Technology", "en"));
            questionDao.insert(new Questions("What is the meaning of CPU","Central Processing Unit", "Critical Processing Unit", "Crucial Processing Unit","Central Printing Unit", 1,"Technology", "en"));


            questionDao.insert(new Questions("מי עיצבה את המעבד הראשון","אפל", "דל", "אינטל","IBM", 3,"טכנולוגיה", "en"));
            questionDao.insert(new Questions("מה מהבאים אינו דפדפן אינטרנט","כרום", "גוגל דרייב", "ספארי","אדג'", 2,"טכנולוגיה", "he"));
            questionDao.insert(new Questions("איזו חברה השיקה מחשב בשם ליסה","אפל", "מייקרוסופט", "אינטל","IBM", 1,"טכנולוגיה", "he"));
            questionDao.insert(new Questions("מה ראשי התיבות של IT","Integrated Technology", "Individual Technology", "Information Technology","Implied Technology", 3,"טכנולוגיה", "he"));
            questionDao.insert(new Questions("מה מהבאים אינו שפת תכנות","PHP", "C++", "C#","Docker", 4,"טכנולוגיה", "he"));
            questionDao.insert(new Questions("מתי הומצא האינטרנט","1968", "1972", "1989","1992", 1,"טכנולוגיה", "he"));
            questionDao.insert(new Questions("כמה ביטים יש בבייט אחד","16 ביטים", "8 ביטים", "24 ביטים","12 ביטים", 2,"טכנולוגיה", "he"));
            questionDao.insert(new Questions("מה ראשי התיבות של CPU","Central Processing Unit", "Critical Processing Unit", "Crucial Processing Unit","Central Printing Unit", 1,"טכנולוגיה", "he"));
            Log.d("TRIVIA", "doInBackground: end insert");

            return null;
        }
    }
}



