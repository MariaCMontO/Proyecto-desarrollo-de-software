import React from 'react'
import styles from './Ordenes.module.css'
  
export default function Ordenes() {

    // Hasta el return es javascript


  return (
    // Dentro del return es HTML (JSX)
    <div className='cuadroPrincipal'>
      <div className='cuadroSecundario'>
        <img className='Logo' src="/Logo.png" alt="" />
        <h1 className='titulo'>
          YA CASI ESTA LISTA TU ORDEN!
        </h1>
        {/* Primer cuadro donde esta la primera imagen de hamburguesa y sus datos */}
        <div className='primerCuadro'>  

        {/* Segundo cuadro donde esta la segunda imagen de hamburguesa y sus datos */}
        </div>
        <div className='segundoCuadro'>

        </div>
        {/* Tercer cuadro donde esta los valores de la compra */}
        <div className='tercerCuadro'>
          <p className='texto'>
            Subtotal:
          </p>
          <p className='texto'>
            Propina(10%):
          </p>
          <p className='total'>
            Total:
          </p>
        </div>
        {/* Cuarto cuadro donde esta el input del metodo de pago */}
        <div className='cuartoCuadro'>
          <p className='texto'>
            Metodo de pago:
          </p>
          <input className='input' type="text" />
        </div>
        {/* Quinto cuadro donde estan los datos de la tarjeta */}
        <div className='quintoCuadro'>
          <p className='texto'>
            Numero de tarjeta:
          </p>
          <input className='input' type="text" />
          <p className='texto'>
            Nombre de la tarjeta:
          </p>
          <input className='input' type="text" />
          <p className='texto'>
            CVV 
          </p>
          <input className='input' type="text" />
        </div>
        {/* Boton para confirmar la compra */}
        <button className='boton'>
          Finalizar compra
        </button>
      </div>
    </div>
  )
}
