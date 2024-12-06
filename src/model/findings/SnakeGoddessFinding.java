package model.findings;



// I don't want to mess with html right now, the HTML enchantment was created using chatGPT
/**
 * Snake Goddess Statue
 * <p>
 * This class represents a snake goddess statue The discovery of the snake goddess does not directly reward the player with points.
 * Instead, when the game ends, the player's points are calculated using the following table:
 * </p>
 * <table style="border: 1px solid black; border-collapse: collapse; text-align: center;">
 *      <caption></caption>
 *   <thead>
 *     <tr>
 *       <th style="border: 1px solid black; padding: 5px;">Number of Statues</th>
 *       <th style="border: 1px solid black; padding: 5px;">Scores</th>
 *     </tr>
 *   </thead>
 *   <tbody>
 *     <tr>
 *       <td style="border: 1px solid black; padding: 5px;">0</td>
 *       <td style="border: 1px solid black; padding: 5px;">0</td>
 *     </tr>
 *     <tr>
 *       <td style="border: 1px solid black; padding: 5px;">1</td>
 *       <td style="border: 1px solid black; padding: 5px;">-20</td>
 *     </tr>
 *     <tr>
 *       <td style="border: 1px solid black; padding: 5px;">2</td>
 *       <td style="border: 1px solid black; padding: 5px;">-15</td>
 *     </tr>
 *     <tr>
 *       <td style="border: 1px solid black; padding: 5px;">3</td>
 *       <td style="border: 1px solid black; padding: 5px;">10</td>
 *     </tr>
 *     <tr>
 *       <td style="border: 1px solid black; padding: 5px;">4</td>
 *       <td style="border: 1px solid black; padding: 5px;">15</td>
 *     </tr>
 *     <tr>
 *       <td style="border: 1px solid black; padding: 5px;">5</td>
 *       <td style="border: 1px solid black; padding: 5px;">30</td>
 *     </tr>
 *     <tr>
 *       <td style="border: 1px solid black; padding: 5px;">6</td>
 *       <td style="border: 1px solid black; padding: 5px;">50</td>
 *     </tr>
 *   </tbody>
 * </table>
 */
public class SnakeGoddessFinding implements Finding {

    @Override
    public void collectFinding() {

    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public boolean isCollectable() {
        return false;
    }
}
