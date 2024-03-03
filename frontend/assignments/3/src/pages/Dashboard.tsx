import styled from "styled-components"
import BasicTabs from "../components/BasicTabs"

const DashboardContainer = styled.div`
    padding-inline: 20px;
`

export function Dashboard() {
    return (
        <DashboardContainer>
            <BasicTabs />
        </DashboardContainer>
    )
}