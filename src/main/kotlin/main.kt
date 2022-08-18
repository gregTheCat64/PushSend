import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import java.io.FileInputStream


fun main() {
    val options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(FileInputStream("src/fcm.json")))
        .build()

    val topic = "Ученые узнали, что поможет будущим колонистам Марса выращивать еду"
    val text =   "Если человечество однажды совершит межпланетный прыжок с Земли на Марс, то людям придется приспосабливаться. Нам нужно понимать, как выращивать еду, строить укрытия и оказывать срочную помощь.Многие видели фильм «Марсианин», в котором главный герой выживает на Марсе на одном только картофеле. Однако это может быть очень далеко от той реальности, которая предстоит первым колонистам Красной планеты. Потому что недавно внимание ученых привлекла люцерна. \n" +
            "\n" +
            "Ученые обнаружили, что эта трава хорошо растет на вулканической почве, имитирующей марсианский реголит, а также ее можно превратить в удобрение для выращивания репы, редиса и даже салата."

    val token = ""
    FirebaseApp.initializeApp(options)

    val message = Message.builder()
        .putData("action", "SHARE")
        .putData("content", """{
          "userId": 1,
          "userName": "Gregory",
          "postId": 3,
          "postAuthor": "Netology"
        }""".trimIndent())
        .setToken(token)
        .build()

    val newPostMessage = Message.builder()
        .putData("action", "NEW_POST")
        .putData("content", """{
         "postAuthor": "Григорий Кот",
         "postText": "$text",
         "postTopic": "$topic"
          }""".trimIndent())
        .setToken(token)
        .build()

    FirebaseMessaging.getInstance().send(newPostMessage)
}