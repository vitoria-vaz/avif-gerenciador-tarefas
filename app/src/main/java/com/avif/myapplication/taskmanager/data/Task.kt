// Disabilita a correcao ortografica
@file:Suppress("SpellCheckingInspection")

package com.avif.myapplication.taskmanager.data

data class Task(
    val id: Int,
    val title: String,
    val body: String? = null,
    val startTime: String,
    val endTime: String
)

/** ChatGPT - início
 * Prompt: Traduza as strings do código para português
 *
 */
val taskList = listOf(
        Task(
            1,
            "Lavar Roupa",
            "Lavar e dobrar roupas",
            "10:00",
            "11:00"
        ),
        Task(
            2,
            "Limpar Cozinha",
            "Lavar louça, limpar bancadas e passar pano no chão",
            "11:30",
            "12:30"
        ),
        Task(
            3,
            "Aspirar Sala de Estar",
            "Limpar carpetes e móveis",
            "13:00",
            "14:00"
        ),
        Task(
            4,
            "Regar Plantas",
            "Regar plantas internas e externas",
            "15:00",
            "16:00"
        ),
        Task(
            5,
            "Preparar Jantar",
            "Preparar uma refeição para a família",
            "18:00",
            "19:00"
        ),
        Task(
            6,
            "Limpar Banheiros",
            "Limpar pias, vasos sanitários, chuveiros e banheiras",
            "9:00",
            "10:00"
        ),
        Task(
            7,
            "Organizar Guarda-Roupa",
            "Separar e dobrar roupas e organizá-las no armário",
            "11:00",
            "12:00"
        ),
        Task(
            8,
            "Tirar Poeira dos Móveis",
            "Limpar e polir mesas, prateleiras e outros móveis",
            "14:00",
            "15:00"
        ),
        Task(
            9,
            "Limpar Janelas",
            "Lavar e limpar janelas e espelhos",
            "16:30",
            "17:30"
        ),
        Task(
            10,
            "Levar o Lixo Para Fora",
            "Coletar e descartar o lixo e a reciclagem",
            "20:00",
            "21:00"
        )
    )
/** ChatGPT - final */
