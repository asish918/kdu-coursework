export type Skills = {
    id: number;
    skill: string;
}

export type Hobbies = {
    id: number;
    hobby: string;
}

export type User = {
    name: string;
    fullName: string;
    qualification: string;
    skills: Skills[];
    hobbies: Hobbies[];
}