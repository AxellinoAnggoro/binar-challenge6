//package com.axellinoanggoro.binar_challenge6.view
//
//import com.axellinoanggoro.binar_challenge6.R
//import android.content.Intent
//import android.content.SharedPreferences
//import android.widget.Button
//import android.widget.EditText
//import android.widget.Toast
//import com.google.firebase.auth.FirebaseAuth
//import org.junit.Before
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.mockito.Mock
//import org.mockito.MockitoAnnotations
//import org.mockito.ArgumentMatchers.any
//import org.mockito.ArgumentMatchers.anyInt
//import org.mockito.ArgumentMatchers.anyString
//import org.mockito.Mockito.*
//import org.mockito.junit.MockitoJUnitRunner
//
//@RunWith(MockitoJUnitRunner::class)
//class LoginActivityTest {
//
//    private lateinit var loginActivity: LoginActivity
//
//    @Mock
//    private lateinit var mockEmailEditText: EditText
//
//    @Mock
//    private lateinit var mockPasswordEditText: EditText
//
//    @Mock
//    private lateinit var mockLoginButton: Button
//
//    @Mock
//    private lateinit var mockFirebaseAuth: FirebaseAuth
//
//    @Mock
//    private lateinit var mockToast: Toast
//
//    @Mock
//    private lateinit var mockSharedPreferences: SharedPreferences
//
//    @Before
//    fun setUp() {
//        MockitoAnnotations.initMocks(this)
//
//        loginActivity = spy(LoginActivity())
//        doReturn(mockEmailEditText).`when`(loginActivity).findViewById<EditText>(R.id.loginEmail)
//        doReturn(mockPasswordEditText).`when`(loginActivity).findViewById<EditText>(R.id.loginPassword)
//        doReturn(mockLoginButton).`when`(loginActivity).findViewById<Button>(R.id.loginBtn)
//        doReturn(mockFirebaseAuth).`when`(loginActivity).auth
//        doReturn(mockSharedPreferences).`when`(loginActivity).getSharedPreferences(anyString(), anyInt())
////        doReturn(mockToast).`when`(loginActivity).createToast(anyString(), anyInt())
//    }
//
//    @Test
//    fun testValidLogin() {
//        // Set up test data
//        val email = "valid-email@example.com"
//        val password = "password123"
//
//        // Set up mocked behavior
//        doAnswer {
//            val authStateListener = it.arguments[0] as FirebaseAuth.AuthStateListener
//            authStateListener.onAuthStateChanged(mockFirebaseAuth)
//            null
//        }.`when`(mockFirebaseAuth).addAuthStateListener(any(FirebaseAuth.AuthStateListener::class.java))
//
//        doAnswer {
//            val expectedIntent = Intent(loginActivity, HomeActivity::class.java)
//            verify(loginActivity).startActivity(expectedIntent)
//            verify(loginActivity).finish()
//            null
//        }.`when`(loginActivity).startActivity(any(Intent::class.java))
//
//        // Perform login
//        loginActivity.firebaseAuthLogin(email, password)
//
//        // Verify the expected behavior
//        verify(mockFirebaseAuth).signInWithEmailAndPassword(email, password)
//        verify(mockToast).show()
//    }
//
//    @Test
//    fun testInvalidEmail() {
//        // Set up test data
//        val email = "invalid-email"
//        val password = "password123"
//
//        // Perform login
//        loginActivity.firebaseAuthLogin(email, password)
//
//        // Verify the expected behavior
//        verify(mockEmailEditText).error = "Invalid Email"
//        verify(mockEmailEditText).requestFocus()
//        verify(mockFirebaseAuth, never()).signInWithEmailAndPassword(email, password)
//        verify(mockToast, never()).show()
//    }
//
//    @Test
//    fun testEmptyPassword() {
//        // Set up test data
//        val email = "valid-email@example.com"
//        val password = ""
//
//        // Perform login
//        loginActivity.firebaseAuthLogin(email, password)
//
//        // Verify the expected behavior
//        verify(mockPasswordEditText).error = "Password still empty"
//        verify(mockPasswordEditText).requestFocus()
//        verify(mockFirebaseAuth, never()).signInWithEmailAndPassword(email, password)
//        verify(mockToast, never()).show()
//    }
//}