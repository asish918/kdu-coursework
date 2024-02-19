import { CgProfile } from "react-icons/cg";
import { FaSuitcase } from "react-icons/fa6";
import { User } from '../../apptypes'
import './Main.scss'

interface MainProps {
    userDetails: User;
}

export default function Main({ userDetails, children }: MainProps & React.PropsWithChildren) {
    return (
        <main className="main-container">
            <h1 className="main-container__name">
                <CgProfile className="icon" />
                {userDetails.name}
            </h1>
            <p className="main-container__full-name">{userDetails.fullName}</p>
            <h1 className="main-container__designation">
                <FaSuitcase className="icon" />
                {userDetails.qualification}
            </h1>
            {children}
        </main>
    )
}