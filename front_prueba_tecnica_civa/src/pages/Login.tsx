import React from "react";
import '../../public/css/login.css'

const LoginPage: React.FC = (): JSX.Element => {

    const LOGIN_ENDPOINT = 'http://localhost:8080/api/v1/auth/login'

    const login = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault()

        const data = Object.fromEntries(new FormData(e.target as HTMLFormElement));
        console.log(data)

        try {
            const response: Response = await fetch(LOGIN_ENDPOINT, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(data),
            })

            if (response.ok) {
                const credentials = await response.json()
                localStorage.setItem('credentials', JSON.stringify(credentials))
                window.location.href = '/buses'
            }

        } catch (e) {
            console.log(e)
        }

    }

    return (
        <>
            <h1 className="form-title">Iniciar Sesión</h1>
            <form className="form-container" onSubmit={login}>
                <div className="form-group">
                    <label>Email: </label>
                    <input name="email" type="text" placeholder="email..." className="form-input" />
                </div>
                <div className="form-group">
                    <label>Password: </label>
                    <input name="password" type="password" placeholder="password..." className="form-input" />
                </div>
                <button type="submit" className="form-button">Iniciar Sesión</button>
            </form>
        </>
    )
}

export default LoginPage