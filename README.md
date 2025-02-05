[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/mZjgzCSj)
<h1> Assignment 1 </h1>

<ul>
  <!-- Relationship is one-to-many relationship between Project and Task -->
  <li>Copy the following command containing the url <strong>git clone https://github.com/SITE-ADA/as1-spring-boot-jpa-app-Ravan002.git</strong> to clone the source code from github.
    </li>
  <li>Open the folder named <strong>assignment1</strong> in IntelliJ IDEA</li>
  <li>Open terminal run run the following command <strong>gradle bootRun</strong> to start the application</li>
  <li>Open any browser and go to <strong>http://localhost:8080/</strong></li>
  <li>You can update, delete, view, and create new Course and Student</li>
  <li> Link to my youtube video: https://youtu.be/ih3dN_aulbI</li>
</ul>

<h3> Creating a simple crud application. </h3>

Dear students,

In this assignment, you need to submit your Spring Boot application similar to what we practiced in class. It requires
additional reading and practicing. The requirements are simple:

<h4> Task related: (70%) </h4>
<ul>
  <!-- (use different entities than those used in class - before you pick, make sure you check the Discussion Forum). -->
  <li>Create two entities <strong>Note:</strong> these entities should have either one-to-many or many-to-many
    bidirectional relationship.</li>
  <li>Use H2 Database to store the data (make sure you add some records before launching the application).</li>
  <li>Enable the user to do operations like Create, Retrieve, Update, Delete (e.g., create a new user, list all users,
    update an existing user, delete a user).</li>
  <li>Use your design knowledge from WM1 to enhance the project with CSS files and make it user friendly.</li>
  <li>Add query methods to the repository (e.g., findStudentByCourseName).</li>
  <li>Write additional JPA @Query (non-native)</li>
</ul>

<h4> Task related: (10%) </h4>
<ul>
  <li>Create a home page where you provide links to the pages (e.g., students and courses separately).</li>
  <li>Design it as you prefer using your design knowledge from WM1 to have a user friendly page.</li>
</ul>

<h4> Task related: (20%) </h4>
<ul>
  <li>Create Add/Remove for the cross-relationship (like removal or addition of courses for the Student).</li>
  <li>Write native queries with some advanced SQL.</li>
</ul>

<h4> Bonus: (10 %) </h4>
<ul>
  <li><strong>Bonus (3%):</strong> Add Pagination to each view where you list entities.</li>
  <li><strong>Bonus (3%):</strong> Add Sorting to each view where you list entities based on each field.</li>
  <li><strong>Bonus (4%):</strong> Add Filtration to each view where you list entities based on each field.</li>
</ul>

<h4> Submission related: </h4>
<ul>
  <li> Make sure you configured your git client (i.e., username and email is set). Use firstname_lastname as the
    username. </li>
  <li> Please, also ensure that you regularly check, add and commit your changes to the remote repo so that we can
    see
    your progress. </li>
  <li>Things to be submitted to the BB grader in a .zip format:
    <ol>
      <li>Documentation pages of your app created by the Gradle, (during the development use javadoc comments
        appropriately)</li>
      <li>This README.md file updated to have
        <ul>
          <li>the instructions on how to start and use the application</li>
          <li>link to the video recording</li>
        </ul>
      </li>
      <li>Application source after <em>./gradlew clean</em></li>
    </ol>
  </li>
</ul>

<p><b>Note:</b> Please work every day; this is definitely not a task you can complete on the last day!
  To keep track of the progress, create a git repository, commit, and push each change (ideally at least every time
  you
  complete a feature) to the repository.
  Ensure that you have at least <strong>1 commit a day</strong> STARTING from <strong>March 7<sup>th</sup></strong>
  otherwise you will lose points.
</p>
<p>For <strong>late submissions</strong> please refer to the SYLLABUS.</p>

This assignment will give you <strong>15%</strong> of the total. <br />
<em> Good luck! </em> <br />
<em> NS </em>