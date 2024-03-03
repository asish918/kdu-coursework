import styled from 'styled-components';

const GridContainer = styled.div`
  display: grid;
  grid-template-columns: repeat(6, 170px);
  grid-template-rows: repeat(4, 125px);
  margin-bottom: 30px;
  border: 2px solid black;
`;

const GridCell = styled.div`
  border: 1px dashed #ccc;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const GridComponent = () => {
  return (
    <GridContainer>
      {Array.from({ length: 24 }, (_, index) => (
        <GridCell key={index}>
        </GridCell>
      ))}
    </GridContainer>
  );
};

export default GridComponent;
