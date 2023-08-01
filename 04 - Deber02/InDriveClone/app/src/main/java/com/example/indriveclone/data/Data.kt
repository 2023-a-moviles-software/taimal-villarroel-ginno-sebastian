package com.example.indriveclone.data

import com.example.indriveclone.models.RequestCity
import com.example.indriveclone.models.RequestCityToCity


class Data {
    fun insertDataRequestCity(): List<RequestCity> {

        val requestList = mutableListOf<RequestCity>()

        val requestRequestCity01 = RequestCity(
            // User
            "Karen",
            3.8,
            "(8)",
            3,
            // Trip Details
            "Av. Jaime Roldos Aguilera 1-165",
            "Parroquia San Francisco",
            "Supermaxi",
            "Victor Gomez Jurado, Ibarra",
            1.0,
            2.2
        )

        val requestRequestCity02 = RequestCity(
            // User
            "Angie",
            4.8,
            "(362)",
            5,
            // Trip Details
            "Obispo Mosquera 11-27",
            "Parroquia El Sagrario",
            "Galo Larrea",
            "Ibarra",
            1.25,
            2.4
        )

        val requestRequestCity03 = RequestCity(
            // User
            "Kathii",
            4.6,
            "(89)",
            2,
            // Trip Details
            "Portoviejo 25-63",
            "Parroquia Alpachaca",
            "Hosteria Agustin Delgado",
            "San Antonio de Ibarra",
            2.25,
            2.9
        )

        requestList.add(requestRequestCity01)
        requestList.add(requestRequestCity02)
        requestList.add(requestRequestCity03)
        requestList.add(requestRequestCity01)
        requestList.add(requestRequestCity02)
        requestList.add(requestRequestCity03)

        return requestList
    }

    fun insertDataRequestCityToCity(): List<RequestCityToCity> {
        val requestCityToCityList = mutableListOf<RequestCityToCity>()

        val requestCityToCity01 = RequestCityToCity(
            "Tarifa negocible por 2 pasajeros",
            "Cash",
            "Viaje Privado",
            "mar 1 agosto",
            "12:45",
            "Parroquia El Sagraria, 8VRJ+M46, Av. Atahualpa, Ibarra, Ecuador",
            "Aeropuerto Internacional Mariscal Sucre, Quito, Ecuador",
            "Publicado hace 3 horas",
            "Doris Solis"
        )

        val requestCityToCity02 = RequestCityToCity(
            "Tarifa negocible por 6 pasajeros",
            "Cash",
            "Viaje Privado",
            "vie 11 agosto",
            "05:45",
            "Parque Ciudad Blanca, Avenida Camilo Ponce Enriquez, Ibarra, Ecuador",
            "Ambato, Ecuador",
            "Publicado hace 5 dias",
            "Byran Alexander"
        )


        requestCityToCityList.add(requestCityToCity01)
        requestCityToCityList.add(requestCityToCity02)
        requestCityToCityList.add(requestCityToCity01)
        requestCityToCityList.add(requestCityToCity02)

        return requestCityToCityList
    }
}