import { FaHeart } from "react-icons/fa";
import { Hobbies } from '../../apptypes'
import './Hobbies.scss'

interface HobbyProps {
    hobbies: Hobbies[];
}

export default function HobbiesSection({ hobbies }: Readonly<HobbyProps>) {
    return (
        <section className="hobbies">
            <h1 className='hobbies__heading'>
                <FaHeart className="icon" />
                Hobbies
            </h1>

            <ul className='hobbies__list'>
                {
                    hobbies.map(hobby => (
                        <li key={hobby.id}>{hobby.hobby}</li>
                    ))
                }
            </ul>
        </section>
    )
}