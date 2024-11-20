import React from "react"

const RegisterPage: React.FC = (): JSX.Element => {

    const REGISTER_ENDPOINT = 'http://localhost:8080/api/v1/auth/register'

    const register = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault()

        const data = Object.fromEntries(new FormData(e.target as HTMLFormElement));

        const response = await fetch(REGISTER_ENDPOINT, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
        }).catch(e => console.log(e))

        if (response) {
            const body = await response.json()
            console.log(body)
        }

    }

    return (
        <>
            <h1 className="form-title">Registro</h1>
            <form className="form-container" onSubmit={register}>
                <div className="form-group">
                    <label>Usuario: </label>
                    <input name="username" type="text" placeholder="usuario..." className="form-input" />
                </div>
                <div className="form-group">
                    <label>Email: </label>
                    <input name="email" type="text" placeholder="email..." className="form-input" />
                </div>
                <div className="form-group">
                    <label>Password: </label>
                    <input name="password" type="password" placeholder="password..." className="form-input" />
                </div>
                <button type="submit" className="form-button">Crear cuenta</button>
            </form>
        </>
    )
}

export default RegisterPage
