import React, { useEffect, useState } from "react";
import Credentials from "../types/credentials";
import '../../public/css/busPage.css'
import Bus from "../types/bus";

const BusPage: React.FC = (): JSX.Element => {

    const BUS_LIST_ENDPOINT = 'http://localhost:8080/api/v1/bus'
    const [credential, setCredentials] = useState<Credentials | null>(null)
    const [buses, setBuses] = useState<Bus[] | null>([])

    useEffect(() => {
        const savedCredentials = localStorage.getItem('credentials');

        if (savedCredentials) {
            setCredentials(JSON.parse(savedCredentials));
        }
    }, []);

    useEffect(() => {
        const fetchBuses = async () => {
            if (!credential?.token) return;

            try {
                const response = await fetch(BUS_LIST_ENDPOINT, {
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${credential.token}`,
                    },
                });

                if (response.ok) {
                    const busList: Bus[] = await response.json();
                    setBuses(busList);
                } else {
                    console.error('Failed to fetch buses:', response.status);
                }
            } catch (error) {
                console.error('Error fetching buses:', error);
            }
        };

        fetchBuses()
    }, [credential])

    return (
        <table className="bus-table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Bus Number</th>
                    <th>License Plate</th>
                    <th>Features</th>
                    <th>Brand</th>
                    <th>Status</th>
                    <th>Visualizar info</th>
                </tr>
            </thead>
            <tbody>
                {buses?.map(bus => (
                    <TableBusRow key={bus.id} bus={bus} />
                ))}
            </tbody>
        </table>
    )
}

const TableBusRow: React.FC<{ bus: Bus }> = ({ bus }): JSX.Element => {

    const viewInfo = () => {
        alert(JSON.stringify(bus))
    }

    return (
        <tr>
            <td>{bus.id}</td>
            <td>{bus.busNumber}</td>
            <td>{bus.licensePlate}</td>
            <td>{bus.features}</td>
            <td>{bus.brand}</td>
            <td>{bus.status}</td>
            <td><button onClick={viewInfo}>Ver info</button></td>
        </tr>
    )
}


export default BusPage