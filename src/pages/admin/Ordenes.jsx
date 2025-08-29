import React from 'react'
import Styles from './Ordenes.module.css'
export default function Ordenes() {

    // Hasta el return es javascript


  return (
    // Dentro del return es HTML (JSX)
    <div className={Styles.cuadroPrincipal}>
      <div className={Styles.cuadroSecundario}>
        <img className={Styles.Logo} src="/Logo.png" alt="" />
        <h1 className={Styles.titulo}>
          YA CASI ESTA LISTA TU ORDEN!
        </h1>
        {/* Primer cuadro donde esta la primera imagen de hamburguesa y sus datos */}
        <div className={Styles.primerCuadro}>  

        {/* Segundo cuadro donde esta la segunda imagen de hamburguesa y sus datos */}
        </div>
        <div className={Styles.segundoCuadro}>

        </div>
        {/* Tercer cuadro donde esta los valores de la compra */}
        <div className={Styles.tercerCuadro}>
          <p className={Styles.texto}>
            Subtotal:
          </p>
          <p className={Styles.texto}>
            Propina(10%):
          </p>
          <p className={Styles.total}>
            Total:
          </p>
        </div>
        {/* Cuarto cuadro donde esta el input del metodo de pago */}
        <div className={Styles.cuartoCuadro}>
          <p className={Styles.texto}>
            Metodo de pago:
          </p>
          <input className={Styles.inputUno} type="text" />
        </div>
        {/* Quinto cuadro donde estan los datos de la tarjeta */}
        <div className={Styles.quintoCuadro}>
          <p className={Styles.texto}>
            Numero de tarjeta:
          </p>
          <input className={Styles.inputDos} type="number" />
          <p className={Styles.texto}>
            Nombre de la tarjeta:
          </p>
          <input className={Styles.inputTres} type="text" />
          <p className={Styles.texto}>
            CVV:
          </p>
          <input className={Styles.inputCuatro} type="text" />
        </div>
        {/* Boton para confirmar la compra */}
        <button className={Styles.boton}>
          Finalizar compra
        </button>
      </div>
    </div>
  )
}
