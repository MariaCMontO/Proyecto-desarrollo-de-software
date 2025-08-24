import styles from './Registro.module.css'

export default function Registration() {
  return (
    <>
    <main>
        <div>
            <img className={styles.vanished} src="../../public/fondo_registro.png" alt="Fondo" />
        </div>

        <section className={styles.login}>
            <div className={styles.formulario}>
                <h2>Registrate</h2>
                <form>
                    <label for="nombre">Nombre: </label>
                    <input id="nombre" type="text" placeholder="Nombres Apellidos"/>

                    <label for="email">Email: </label>
                    <input id="email" type="text" placeholder="correo@gmail.com"/>

                    <label for="contraseña">Contraseña: </label>
                    <input id="contraseña" type="password" placeholder="***********"/>

                    <label for="celular">Celular: </label>
                    <input id="celular" type="text" placeholder="123 456 7890"/>

                    <label for="direccion">Direccion: </label>
                    <input id="direccion" type="text" placeholder="##########"/>

                    <input type="checkbox" name="acepto" value="acepto" id="terminos"/>
                    <label id="term_cond" for="checkbox">Aceptar términos y condiciones.</label>

                    <input id="registrarse" type="submit" value="REGISTRARSE"/>
                </form>
            </div>
        </section>
    </main>
    </>
  )
}
