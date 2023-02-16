package ru.cft.shift2023winter.data.converter

import ru.cft.shift2023winter.data.model.UserModel
import ru.cft.shift2023winter.domain.entity.*

class RandomUserConverter {
    /*fun convertRandomUser(from: RandomUserModel): RandomUser?{
        var randomUser: RandomUser? = null

        from.listUsers.forEach {
            if (randomUser != null) {
                randomUser.listUsers.add(convertUser(it))
            }
        }
        if (randomUser != null) {
            randomUser.info = Info(
                seed = from.info.seed,
                results = from.info.results,
                page = from.info.page,
                version = from.info.version
            )
        }

        return randomUser ?: null
    }*/


     fun convertUser(from: UserModel): User =
        User(
            gender = from.gender,
            name = from.name.title + ". " + from.name.first + " " + from.name.last,
            location = Location(
                street = from.location.street.name + ", " + from.location.street.number.toString(),
                city = from.location.city,
                state = from.location.state,
                country = when(from.location.country){
                    "Ukraine" -> "Russia"
                    else -> from.location.country },
                postcode = from.location.postcode,
                coordinates = from.location.coordinates.latitude + ", " + from.location.coordinates.longitude,
                timezone = Timezone(
                    offset = from.location.timezone.offset,
                    description = from.location.timezone.description
                )
            ),
            email = from.email,
            login = Login(
                uuid = from.login.uuid,
                username = from.login.username,
                password = from.login.password,
                salt = from.login.salt,
                md5 = from.login.md5,
                sha1 = from.login.sha1,
                sha256 = from.login.sha256
            ),
            dateOfBirth = DateOfBirth(
                date = from.dateOfBirth.date,
                age = from.dateOfBirth.age
            ),
            registered = Registered(
                date = from.registered.date,
                age = from.registered.age
            ),
            phone = from.phone,
            cell = from.cell,
            id = from.email,
            picture = Picture(
                large = from.picture.large,
                medium = from.picture.medium,
                thumbnail = from.picture.thumbnail
            ),
            nat = from.nat
        )
}