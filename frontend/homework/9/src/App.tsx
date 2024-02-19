import HobbiesSection from './components/hobbies/Hobbies'
import Main from './components/main/Main'
import SkillSection from './components/skills/Skills'

import { jsonData } from './data';

export default function App() {
    return (
        <Main userDetails={jsonData}>
            <SkillSection skills={jsonData.skills} />
            <HobbiesSection hobbies={jsonData.hobbies} />
        </Main>
    )
}