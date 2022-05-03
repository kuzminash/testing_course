import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.GrantPermissionRule
import androidx.test.runner.AndroidJUnit4
import androidx.test.uiautomator.*
import org.hamcrest.Matchers.notNullValue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep
import java.util.jar.Manifest

private const val BASIC_SAMPLE_PACKAGE = "com.vkontakte.android"
private const val LAUNCH_TIMEOUT = 5000L
private const val STRING_TO_BE_TYPED = "UiAutomator"

@RunWith(androidx.test.ext.junit.runners.AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 26)
class ChangeTextBehaviorTest2 {

    private lateinit var device: UiDevice

    @Before
    fun startMainActivityFromHomeScreen() {
        // Initialize UiDevice instance
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        // Start from the home screen
        device.pressHome()

        // Wait for launcher
        val launcherPackage: String = device.launcherPackageName
        assertThat(launcherPackage, notNullValue())
        device.wait(
            Until.hasObject(By.pkg(launcherPackage).depth(0)),
            LAUNCH_TIMEOUT
        )

        // Launch the app
        val context = ApplicationProvider.getApplicationContext<Context>()
        val intent = context.packageManager.getLaunchIntentForPackage(
            BASIC_SAMPLE_PACKAGE
        )?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)

        // Wait for the app to appear
        device.wait(
            Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
            LAUNCH_TIMEOUT
        )
    }

    @Test
    fun loginTest() {
        val editLogin: UiObject = device.findObject(
            UiSelector().text("Email или телефон")
        )
        editLogin.text = "89214075292"
        val enterButton: UiObject = device.findObject(
            UiSelector().textContains("Войти")
        )
        enterButton.click()
        
        val editPassword: UiObject = device.findObject(
            UiSelector().text("Пароль")
        )
        editPassword.text = "Isegem13"
        val enterButtonTwo: UiObject = device.findObject(
            UiSelector().text("Продолжить")
        )
        enterButtonTwo.click()
        
    }
    
    @Test
    fun textMessage() {
        val messenger: UiObject = device.findObject(
            UiSelector().text("Мессенджер")
        )
        messenger.click()
        
        val dialog: UiObject = device.findObject(
            UiSelector().textContains("Кирилл")
        )
        dialog.click()
        val message: UiObject = device.findObject(
            UiSelector().text("Сообщение")
        )
        message.click()
        message.text = "Хочу 5 по тестированию, ну пожалуйста, мне для магистратуры"
        
        val send: UiObject = device.findObject(
            UiSelector().descriptionContains("Отправить")
        )
        send.click()
    }
    
    @Test
    fun deleteFriend() {
        val myLink: UiObject = device.findObject(
            UiSelector().descriptionContains("Открыть меню")
        )
        myLink.click()
        
        val profile: UiObject = device.findObject(
            UiSelector().descriptionContains("Мой профиль")
        )
        profile.click()
        
        val friends: UiObject = device.findObject(
            UiSelector().descriptionContains("Друзья")
        )
        friends.click()
        
        val friend: UiObject = device.findObject(
            UiSelector().textContains("Айнур")
        )
        friend.click()
        
        val friendButton: UiObject = device.findObject(
            UiSelector().textContains("В друзьях")
        )
        friendButton.click()
        
        val deleteFriendButton: UiObject = device.findObject(
            UiSelector().textContains("Удалить из друзей")
        )
        deleteFriendButton.click()
        
        val confirmationButton: UiObject = device.findObject(
            UiSelector().text("Да")
        )
        confirmationButton.click()
    }
    
    @Test
    fun leaveGroup() {
        val myLink: UiObject = device.findObject(
            UiSelector().descriptionContains("Открыть меню")
        )
        myLink.click()
        
        val profile: UiObject = device.findObject(
            UiSelector().descriptionContains("Мой профиль")
        )
        profile.click()
        
        val info: UiObject = device.findObject(
            UiSelector().textContains("Подробная информация")
        )
        info.click()
        
        val groups: UiObject = device.findObject(
            UiSelector().textContains("Сообщества")
        )
        groups.click()
        
        //потом я честно зашла обратно
        val group: UiObject = device.findObject(
            UiSelector().textContains("ВКонтакте")
        )
        group.click()
        
        val subscribeButton: UiObject = device.findObject(
            UiSelector().textContains("Вы подписаны")
        )
        subscribeButton.click()
        
        val unsubscribeButton: UiObject = device.findObject(
            UiSelector().textContains("Отписаться")
        )
        unsubscribeButton.click()
    }
    
    @Test
    fun photoMessage() {
        val messenger: UiObject = device.findObject(
            UiSelector().text("Мессенджер")
        )
        messenger.click()
        
        val dialog: UiObject = device.findObject(
            UiSelector().textContains("Саша")
        )
        dialog.click()
        val attachment: UiObject = device.findObject(
            UiSelector().descriptionContains("Прикрепить вложение")
        )
        attachment.click()
        
        val photo: UiObject = device.findObject(
            UiSelector().descriptionContains("Фотография")
        )
        photo.click()
        
        val attach: UiObject = device.findObject(
            UiSelector().textContains("Добавить")
        )
        attach.click()
        
        val send: UiObject = device.findObject(
            UiSelector().descriptionContains("Отправить")
        )
        send.click()
    }
    
    @Test
    fun textPost() {
        val myLink: UiObject = device.findObject(
            UiSelector().descriptionContains("Открыть меню")
        )
        myLink.click()
        
        val profile: UiObject = device.findObject(
            UiSelector().descriptionContains("Мой профиль")
        )
        profile.click()
        
        val newPost: UiObject = device.findObject(
            UiSelector().textContains("Запись")
        )
        newPost.click()
        
        val textPost: UiObject = device.findObject(
            UiSelector().textContains("Что у вас нового?")
        )
        textPost.click()
        textPost.text = "Очень хочу 5"
        
        val post: UiObject = device.findObject(
            UiSelector().descriptionContains("Опубликовать")
        )
        post.click()
    }
}
