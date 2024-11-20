import React from "react";
import '../../public/css/customHeader.css'

const CustomHeader: React.FC = (): JSX.Element => {
    return (
        <header className="header">
            <nav className="nav">
                <a href="/buses" className="nav-link">Lista de buses</a>
                <a href="/" className="nav-link">Iniciar Sesión</a>
                <a href="/register" className="nav-link">Registro</a>
            </nav>
        </header>
    )
}

export default CustomHeader