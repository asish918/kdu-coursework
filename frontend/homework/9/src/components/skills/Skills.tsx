import { FaList } from "react-icons/fa6";
import { Skills } from '../../apptypes'
import './Skills.scss'

interface SkillsProps {
    skills: Skills[];
}

export default function SkillSection({ skills }: Readonly<SkillsProps>) {
    return (
        <section className="skills">
            <h1 className='skills__heading'>
                <FaList className="icon" />
                Skills
            </h1>

            <ul className='skills__list'>
                {
                    skills.map((skill) => (
                        <li key={skill.id}>{skill.skill}</li>
                    ))
                }
            </ul>
        </section>
    )
}