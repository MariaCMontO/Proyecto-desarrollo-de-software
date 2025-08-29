import React from 'react'
import Styles from './IA.module.css'

export default function 

() {
  return (
    <div className={Styles.cuadroPrincipal}>
        <div className={Styles.cuadroSecundario}>
            <img className={Styles.logo} src="/Logo.png" alt="Logo" />
            {/* Titulo del cuadro secundario */}
            <h1>
                ESTAS A UN PASO DE GENERAR TU PEDIDO CON IA!
            </h1>
            {/* Primer cuadrito donde estan las preguntas */}
            <div>
                <p className={Styles.pregunta}>
                    Cuantas personas comeran hoy ? 
                </p>
                <input className={Styles.inputUno} type="number" />
                <p className={Styles.pregunta}>
                    Notas adicionales para tu pedido:
                </p>
                <input className={Styles.inputDos} type="text" />
            </div>
            {/* Segundo cuadrito donde esta el mensaje de rojo */}
            <div>
                <h5>
                    ¡ATENCIÓN ANTOJAD@S! NUESTRA IA PREPARÁ TU PEDIDO BASÁNDOSE EN TUS GUSTOS GUARDADOS EN EL PERFIL. PERFECTO PARA ESOS DÍAS EN LOS QUE TIENES UN ANTOJO MISTERIOSO...¡Y NI TÚ SABES EXACTAMENTE DE QUÉ!
                </h5>
            </div>
            {/* Boton de pagar */}
            <button className={Styles.boton}>
                Generar pedido con IA
            </button>
        </div>
    </div>
  )
}
