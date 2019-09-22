package com.smarttoolfactory.mymarket.data.repository

import com.smarttoolfactory.mymarket.data.model.User
import com.smarttoolfactory.mymarket.data.source.local.dao.UsersDao
import io.reactivex.Maybe
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val usersDao: UsersDao) :
    LoginRepository {

    /**
     * This method checks if there is a user record in database. This is for remembering previous user
     */
    override fun getRegisteredUser(): Maybe<User> {
        return usersDao.getUser()
    }

    /**
     * This method is used for creating mock user on first run for this assignment
     */
    override fun registerUser(user: User): Maybe<Long> {
        return usersDao.insert(user)
    }

    override fun logIn(name: String?, password: String?, rememberMe: Boolean?): Maybe<Long> {

        val user = User(name = name, password = password, rememberUser = rememberMe)
        return usersDao.insert(user)

    }

    override fun logOut(user: User): Maybe<Int> {
        return usersDao.update(user)
    }


}